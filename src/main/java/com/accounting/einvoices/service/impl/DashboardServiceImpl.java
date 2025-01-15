package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.charts.ProductSalesStatDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.service.*;
import lombok.extern.slf4j.Slf4j;
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
    private final CurrencyExchangeService currencyExchangeService;

    public DashboardServiceImpl(InvoiceService invoiceService, InvoiceProductService invoiceProductService, CurrencyExchangeService currencyExchangeService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.currencyExchangeService = currencyExchangeService;
    }


    public List<ProductSalesStatDTO> totalProductsSoldEachDayMonthByCurrency(int year, int startMonth, int endMonth, String currency) {

        List<ProductSalesStatDTO> stats = new ArrayList<>();

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);

        BigDecimal totalAmount;
        ProductSalesStatDTO productSalesStat;

        for (Currency value : Currency.values()) {
            List<InvoiceDTO> invoicesByCurrency = map.get(value);
            if (invoicesByCurrency == null) {
                continue;
            }
            for (InvoiceDTO invoice : invoicesByCurrency) {
                int dayQuantity = invoiceProductService.findAllByInvoiceId(invoice.getId()).stream()
                        .map(InvoiceProductDTO::getQuantity)
                        .reduce(Integer::sum).orElse(0);

                totalAmount = currencyExchangeService.convertToCommonCurrency(invoice.getTotal(), value.name(), currency);

                productSalesStat = ProductSalesStatDTO.builder()
                        .year(year)
                        .month(invoice.getAcceptDate().getMonthValue())
                        .dayOfMonth(invoice.getAcceptDate().getDayOfMonth())
                        .quantity(dayQuantity)
                        .amount(totalAmount )
                        .currency(invoice.getCurrency())
                        .build();

                stats.add(productSalesStat);
            }

        }

        log.info("Product sales stats: {}", stats);

        return stats;
    }


    @Override
    public Map<String, ProductSalesStatDTO> topSellingProductsDesc(int year, int startMonth, int endMonth, String currency) {

        Map<String, ProductSalesStatDTO> stats = new HashMap<>();

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);

        ProductSalesStatDTO productSalesStat;

        for (Currency value : Currency.values()) {

            List<InvoiceDTO> invoicesByCurrency = map.get(value);

            if (invoicesByCurrency == null) {
                continue;
            }

            for (InvoiceDTO invoice : invoicesByCurrency) {

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
                        BigDecimal addedAmount = productSalesStatDTO.getAmount().add(totalAmount);
                        addedAmount = currencyExchangeService.convertToCommonCurrency(addedAmount, value.name(), currency);
                        productSalesStatDTO.setAmount(addedAmount);

                    } else {
                        productSalesStat = ProductSalesStatDTO.builder()
                                .quantity(each.getValue())
                                .amount(currencyExchangeService.convertToCommonCurrency(totalAmount, value.name(), currency))
                                .currency(invoice.getCurrency())
                                .build();
                        stats.put(name, productSalesStat);
                    }
                }
            }

        }

        return stats;
    }


}
