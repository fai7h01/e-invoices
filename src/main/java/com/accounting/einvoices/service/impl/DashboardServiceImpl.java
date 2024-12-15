package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.annotation.ExecutionTime;
import com.accounting.einvoices.client.ExchangeRateClient;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.dto.response.ConversionRates;
import com.accounting.einvoices.dto.response.CurrencyExchangeDTO;
import com.accounting.einvoices.dto.response.ExchangeRateResponse;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.exception.ExchangeRatesNotRetrievedException;
import com.accounting.einvoices.exception.InvoiceNotFoundException;
import com.accounting.einvoices.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final UserService userService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;
    private final ExchangeRateClient exchangeRateClient;

    public DashboardServiceImpl(InvoiceService invoiceService, UserService userService, ClientVendorService clientVendorService,
                                @Lazy ProductService productService, InvoiceProductService invoiceProductService, ExchangeRateClient exchangeRateClient) {
        this.invoiceService = invoiceService;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
        this.exchangeRateClient = exchangeRateClient;
    }


    //@Cacheable(value = "SoldProductsStatsEachDayOfMonth", key = "{#year, #month}")
    public List<ProductSalesStatDTO> totalProductsSoldEachDayMonthByCurrency(int year, int month, String currency) {

        List<ProductSalesStatDTO> stats = new ArrayList<>();

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, month);

        List<InvoiceDTO> invoices = map.get(Currency.valueOf(currency));

        if (invoices == null) {
            throw new InvoiceNotFoundException("Invoices not found.");
        }

        log.info("\n\n>> Invoices from map by currency: {}", invoices);

        int totalQuantity = 0;
        BigDecimal totalAmount = BigDecimal.ZERO;
        ProductSalesStatDTO productSalesStat = ProductSalesStatDTO.builder().build();

        for (InvoiceDTO invoice : invoices) {
            int dayQuantity = invoiceProductService.findAllByInvoiceId(invoice.getId()).stream()
                    .map(InvoiceProductDTO::getQuantity)
                    .reduce(Integer::sum).orElse(0);
            log.info("\n\n>> Day quantity: {}, at november: {}", dayQuantity, invoice.getAcceptDate().getDayOfMonth());

            totalQuantity += dayQuantity;
            totalAmount = totalAmount.add(invoice.getTotal());

            productSalesStat = ProductSalesStatDTO.builder()
                    .year(year)
                    .month(month)
                    .dayOfMonth(invoice.getAcceptDate().getDayOfMonth())
                    .quantity(totalQuantity)
                    .amount(totalAmount)
                    .currency(invoice.getCurrency())
                    .build();
        }
        stats.add(productSalesStat);

        log.info("\n\n last result of product stats: {}", stats);
        return stats;
    }


    @Override
    public List<ProductSalesStatDTO> topSellingProductsDesc(int year, int month, String currency) {

        List<ProductSalesStatDTO> stats = new ArrayList<>();

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, month);

        List<InvoiceDTO> invoices = map.get(Currency.valueOf(currency));

        if (invoices == null) throw new InvoiceNotFoundException("Approved Invoices not found.");

        ProductSalesStatDTO productSalesStat;

        for (int index = 0; index < invoices.size(); index++) {
            List<InvoiceProductDTO> invoiceProducts = invoiceProductService.findAllByInvoiceId(invoices.get(index).getId());
            List<Map.Entry<Pair<ProductDTO, BigDecimal>, Integer>> productQtyEntryList = invoiceProducts.stream()
                    .collect(Collectors.groupingBy(dto -> Pair.of(dto.getProduct(), dto.getPrice()),
                            Collectors.summingInt(InvoiceProductDTO::getQuantity)))
                    .entrySet()
                    .stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .collect(Collectors.toList());

            log.info("\n\n>> Product Quantity Map: {}", productQtyEntryList);

            for (Map.Entry<Pair<ProductDTO, BigDecimal>, Integer> each : productQtyEntryList) {
                BigDecimal totalAmount = each.getKey().getSecond().multiply(BigDecimal.valueOf(each.getValue()));
                productSalesStat = ProductSalesStatDTO.builder()
                        .name(each.getKey().getFirst().getName())
                        .quantity(each.getValue())
                        .amount(totalAmount)
                        .currency(invoices.get(index).getCurrency())
                        .build();
                stats.add(productSalesStat);
            }

        }
        return stats;
    }


    @Cacheable(value = "exchangeRates", key = "#code")
    @Override
    public CurrencyExchangeDTO exchangeRatePairs(String code) {

        return switch (code) {
            case "GEL" -> getGelExchanges();
            case "USD" -> getUsdExchanges();
            case "EUR" -> getEuroExchanges();
            default -> throw new ExchangeRatesNotRetrievedException("Exchange rates could not retrieve.");
        };
    }

    private CurrencyExchangeDTO getGelExchanges() {

        Map<String, ExchangeRateResponse> responseMap = exchangeRateResponsesMap();


        Double usd = 0d;
        Double eur = 0d;
        Double gbp = 0d;
        Double cny = 0d;

        if (Objects.requireNonNull(responseMap.get("USD").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("USD").getConversionRates();
            usd = conversionRates.getGel();

        }

        if (Objects.requireNonNull(responseMap.get("EUR").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("EUR").getConversionRates();
            eur = conversionRates.getGel();

        }

        if (Objects.requireNonNull(responseMap.get("GBP").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("GBP").getConversionRates();
            gbp = conversionRates.getGel();

        }

        if (Objects.requireNonNull(responseMap.get("CNY").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("CNY").getConversionRates();
            cny= conversionRates.getGel();
        }

        return CurrencyExchangeDTO.builder()
                .usd(usd)
                .eur(eur)
                .gbp(gbp)
                .cny(cny)
                .build();

    }

    private CurrencyExchangeDTO getEuroExchanges() {

        Map<String, ExchangeRateResponse> responseMap = exchangeRateResponsesMap();


        if (Objects.requireNonNull(responseMap.get("EUR").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("EUR").getConversionRates();
            Double usd = conversionRates.getUsd();
            Double gbp = conversionRates.getGbp();
            Double cny = conversionRates.getCny();
            Double aud = conversionRates.getAud();

            return CurrencyExchangeDTO.builder()
                    .usd(usd)
                    .cny(cny)
                    .gbp(gbp)
                    .aud(aud)
                    .build();
        } else {
            throw new ExchangeRatesNotRetrievedException("Exception rates could not retrieved.");
        }

    }

    private CurrencyExchangeDTO getUsdExchanges() {

        Map<String, ExchangeRateResponse> responseMap = exchangeRateResponsesMap();


        if (Objects.requireNonNull(responseMap.get("USD").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("USD").getConversionRates();
            Double eur = conversionRates.getEur();
            Double cny = conversionRates.getCny();
            Double gbp = conversionRates.getGbp();
            Double aud = conversionRates.getAud();

            return CurrencyExchangeDTO.builder()
                    .eur(eur)
                    .cny(cny)
                    .gbp(gbp)
                    .aud(aud)
                    .build();
        } else {
            throw new ExchangeRatesNotRetrievedException("Exception rates could not retrieved.");
        }
    }

    private Map<String, ExchangeRateResponse> exchangeRateResponsesMap() {

        return Map.of(
                "USD", exchangeRateClient.getExchanges("USD"),
                "EUR", exchangeRateClient.getExchanges("EUR"),
                "GBP", exchangeRateClient.getExchanges("GBP"),
                "CNY", exchangeRateClient.getExchanges("CNY")
        );
    }



}
