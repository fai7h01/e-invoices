package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.ProductDTO;
import com.accounting.einvoices.dto.response.CurrencyExchangeDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.service.*;
import com.accounting.einvoices.util.BigDecimalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportingServiceImpl implements ReportingService {

    private final ProductService productService;
    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final UserService userService;
    private final ClientVendorService clientVendorService;
    private final DashboardService dashboardService;

    public ReportingServiceImpl(ProductService productService, InvoiceService invoiceService, InvoiceProductService invoiceProductService, UserService userService, ClientVendorService clientVendorService, DashboardService dashboardService) {
        this.productService = productService;
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
        this.dashboardService = dashboardService;
    }

    @Override
    public Map<String, Integer> getSummaryQuantities() {
        return Map.of("total_employees", userService.findAll().size(),
                "total_clients", clientVendorService.findAll().size(),
                "total_products", productService.findAll().size(),
                "total_products_sold", invoiceProductService.sumQuantityOfSoldProducts());
    }

    @Override
    public Map<String, BigDecimal> getFinancialSummaryInSeparateCurrency(int year, int startMonth, int endMonth, String currency) {

        Map<String, BigDecimal> map = new HashMap<>();

        map.put("total_cost", countTotalCostByDateInOneCurrency(year, startMonth, endMonth, currency));
        map.put("total_sales", countTotalSalesByDateInOneCurrency(year, startMonth, endMonth, currency));
        map.put("total_profit_loss", sumProfitLossByDateInOneCurrency(year, startMonth, endMonth, currency));

        return map;
    }





    @Override
    public Map<String, BigDecimal> getFinancialSummaryInOneCurrency(int year, int startMonth, int endMonth, String currency) {

        Map<String, BigDecimal> map = new HashMap<>();

        map.put("total_cost", sumTotalCostOfEachCurrencyInPeriodAndConvert(year, startMonth, endMonth, currency));
        map.put("total_sales", sumTotalSalesOfEachCurrencyInPeriodAndConvert(year, startMonth, endMonth, currency));
//        map.put("total_profit_loss", sumTotalProfitLossByDateAndConvert(year, startMonth, endMonth, currency));

        return map;
    }


    private BigDecimal sumTotalCostOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, BigDecimal> map = new HashMap<>();

        for (Currency value : Currency.values()) {
            BigDecimal total = productService.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, value.name())
                    .stream()
                    .map(productDTO -> {
                        BigDecimal price = productDTO.getPrice();
                        Integer quantityInStock = productDTO.getQuantityInStock();
                        return price.multiply(BigDecimal.valueOf(quantityInStock));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            map.put(value, total);
        }


        BigDecimal totalCostUsd = map.get(Currency.USD);
        BigDecimal totalCostGel = map.get(Currency.GEL);
        BigDecimal totalCostEur = map.get(Currency.EUR);


        return calculateTotalCostOrTotalSales(totalCostUsd, totalCostGel, totalCostEur, currency);
    }

    private BigDecimal sumTotalSalesOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, BigDecimal> map = new HashMap<>();

        for (Currency value : Currency.values()) {
            BigDecimal totalSalesInEachCurrency = countTotalSalesByDateInOneCurrency(year, startMonth, endMonth, value.name());
            map.put(value, totalSalesInEachCurrency);
        }

        BigDecimal totalSalesUsd = map.get(Currency.USD);
        BigDecimal totalSalesGel = map.get(Currency.GEL);
        BigDecimal totalSalesEur = map.get(Currency.EUR);

        return calculateTotalCostOrTotalSales(totalSalesUsd, totalSalesGel, totalSalesEur, currency);
    }

    
    private BigDecimal calculateTotalCostOrTotalSales(BigDecimal totalUsd, BigDecimal totalGel, BigDecimal totalEur, String currency) {

        BigDecimal total = BigDecimal.ZERO;

        Map<String, CurrencyExchangeDTO> currencyExchanges = currencyExchangesMap();

        switch (currency) {

            case "USD":

                BigDecimal getToUsd = currencyExchanges.get("GEL").getUsd();
                BigDecimal eurToUsd = currencyExchanges.get("EUR").getUsd();

                total = total.add(totalUsd).add(totalGel.multiply(getToUsd)).add(totalEur.multiply(eurToUsd));

                break;
            case "GEL":

                BigDecimal usdToGel = currencyExchanges.get("USD").getGel();
                BigDecimal eurToGel = currencyExchanges.get("EUR").getGel();

                total = total.add(totalGel).add(totalUsd.multiply(usdToGel)).add(totalEur.multiply(eurToGel));

                break;
            case "EUR":

                BigDecimal usdToEur = currencyExchanges.get("USD").getEur();
                BigDecimal gelToEur = currencyExchanges.get("GEL").getEur();

                total = total.add(totalEur).add(totalUsd.multiply(usdToEur)).add(totalGel.multiply(gelToEur));

                break;
            default:
                return BigDecimal.ZERO;

        }

        return BigDecimalUtil.format(total);
        
    }

    private Map<String, CurrencyExchangeDTO> currencyExchangesMap() {

        CurrencyExchangeDTO usd = dashboardService.exchangeRatesOf("USD", 1L);
        CurrencyExchangeDTO gel = dashboardService.exchangeRatesOf("GEL", 1L);
        CurrencyExchangeDTO eur = dashboardService.exchangeRatesOf("EUR", 1L);

        return Map.of(
                "USD", usd,
                "GEL", gel,
                "EUR", eur
        );

    }


    private BigDecimal countTotalCostByDateInOneCurrency(int year, int startMonth, int endMonth, String currency) {

        BigDecimal totalCost = BigDecimal.ZERO;
        for (ProductDTO each : productService.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency)) {
            BigDecimal price = each.getPrice();
            int quantity = each.getQuantityInStock();
            BigDecimal cost = price.multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(cost);
        }
        return BigDecimalUtil.format(totalCost);
    }

    private BigDecimal countTotalSalesByDateInOneCurrency(int year, int startMonth, int endMonth, String currency) {
        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoicesByCurrency = map.get(Currency.valueOf(currency));
        return invoicesByCurrency.stream()
                .map(InvoiceDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private BigDecimal sumProfitLossByDateInOneCurrency(int year, int startMonth, int endMonth, String currency) {
        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoicesByCurrency = map.get(Currency.valueOf(currency));
        return invoicesByCurrency.stream()
                .map(invoiceDTO -> invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO.getId())
                        .stream().map(InvoiceProductDTO::getProfitLoss).reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
