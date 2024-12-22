package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.ExchangeRateClient;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.dto.response.ConversionRates;
import com.accounting.einvoices.dto.response.CurrencyExchangeDTO;
import com.accounting.einvoices.dto.response.ExchangeRateResponse;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.exception.exchangeRates.ExchangeRatesNotRetrievedException;
import com.accounting.einvoices.exception.invoice.InvoiceNotFoundException;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ExchangeRateClient exchangeRateClient;

    public DashboardServiceImpl(InvoiceService invoiceService, InvoiceProductService invoiceProductService, ExchangeRateClient exchangeRateClient) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.exchangeRateClient = exchangeRateClient;
    }


    public List<ProductSalesStatDTO> totalProductsSoldEachDayMonthByCurrency(int year, int startMonth, int endMonth, String currency) {

        List<ProductSalesStatDTO> stats = new ArrayList<>();

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);

        List<InvoiceDTO> invoices = map.get(Currency.valueOf(currency));

        if (invoices == null) {
            throw new InvoiceNotFoundException("Invoices not found.");
        }

        int totalQuantity = 0;
        BigDecimal totalAmount = BigDecimal.ZERO;
        ProductSalesStatDTO productSalesStat;

        for (InvoiceDTO invoice : invoices) {

            int dayQuantity = invoiceProductService.findAllByInvoiceId(invoice.getId()).stream()
                    .map(InvoiceProductDTO::getQuantity)
                    .reduce(Integer::sum).orElse(0);

            totalQuantity += dayQuantity;
            totalAmount = totalAmount.add(invoice.getTotal());

            productSalesStat = ProductSalesStatDTO.builder()
                    .year(year)
                    .month(invoice.getAcceptDate().getMonthValue())
                    .dayOfMonth(invoice.getAcceptDate().getDayOfMonth())
                    .quantity(totalQuantity)
                    .amount(totalAmount)
                    .currency(invoice.getCurrency())
                    .build();

            stats.add(productSalesStat);
        }


        return stats;
    }


    @Override
    public Map<String, ProductSalesStatDTO> topSellingProductsDesc(int year, int startMonth, int endMonth, String currency) {

        Map<String, ProductSalesStatDTO> stats = new HashMap<>();

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);

        List<InvoiceDTO> invoices = map.get(Currency.valueOf(currency));

        if (invoices == null) throw new InvoiceNotFoundException("Approved Invoices not found.");

        ProductSalesStatDTO productSalesStat;

        for (InvoiceDTO invoice : invoices) {

            List<InvoiceProductDTO> invoiceProducts = invoiceProductService.findAllByInvoiceId(invoice.getId());

            List<Map.Entry<Pair<ProductDTO, BigDecimal>, Integer>> productQtyEntryList = invoiceProducts.stream()
                    .collect(Collectors.groupingBy(dto -> Pair.of(dto.getProduct(), dto.getPrice()),
                            Collectors.summingInt(InvoiceProductDTO::getQuantity)))
                    .entrySet()
                    .stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .toList();

            for (Map.Entry<Pair<ProductDTO, BigDecimal>, Integer> each : productQtyEntryList) {
                String name = each.getKey().getFirst().getName();
                Integer qty = each.getValue();
                BigDecimal totalAmount = each.getKey().getSecond().multiply(BigDecimal.valueOf(each.getValue()));

                if (stats.containsKey(name)) {

                    ProductSalesStatDTO productSalesStatDTO = stats.get(name);
                    productSalesStatDTO.setQuantity(productSalesStatDTO.getQuantity() + qty);
                    productSalesStatDTO.setAmount(productSalesStatDTO.getAmount().add(totalAmount));

                } else {
                    productSalesStat = ProductSalesStatDTO.builder()
                            .quantity(each.getValue())
                            .amount(totalAmount)
                            .currency(invoice.getCurrency())
                            .build();
                    stats.put(name, productSalesStat);
                }
            }
        }
        return stats;
    }


    @Cacheable(value = "exchangeRates", key = "#code + '_' + (#amount != null ? #amount : 1)")
    @Override
    public CurrencyExchangeDTO exchangeRatesOf(String code, Long amount) {

        ExchangeRateResponse response = exchangeRateClient.getExchanges(code);

        BigDecimal n = (amount == null) ? BigDecimal.ONE : BigDecimal.valueOf(amount);

        if (Objects.requireNonNull(response.getResult()).equals("success")) {
            ConversionRates conversionRates = response.getConversionRates();
            BigDecimal usd = BigDecimal.valueOf(conversionRates.getUsd());
            BigDecimal gbp = BigDecimal.valueOf(conversionRates.getGbp());
            BigDecimal cny = BigDecimal.valueOf(conversionRates.getCny());
            BigDecimal aud = BigDecimal.valueOf(conversionRates.getAud());
            BigDecimal gel = BigDecimal.valueOf(conversionRates.getGel());
            BigDecimal eur = BigDecimal.valueOf(conversionRates.getEur());

            if (amount != null) {

                usd = BigDecimalUtil.format(usd.multiply(n));
                gbp = BigDecimalUtil.format(gbp.multiply(n));
                cny = BigDecimalUtil.format(cny.multiply(n));
                aud = BigDecimalUtil.format(aud.multiply(n));
                gel = BigDecimalUtil.format(gel.multiply(n));
                eur = BigDecimalUtil.format(eur.multiply(n));
            }

            return CurrencyExchangeDTO.builder()
                    .usd(usd)
                    .cny(cny)
                    .gbp(gbp)
                    .aud(aud)
                    .gel(gel)
                    .eur(eur)
                    .build();
        }

        throw new ExchangeRatesNotRetrievedException("Exchange rates could not retrieve.");

    }


}
