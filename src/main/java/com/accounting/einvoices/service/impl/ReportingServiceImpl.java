package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.*;
import com.accounting.einvoices.dto.response.CurrencyExchangeDTO;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.exception.DataIsNotPresentException;
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
        return Map.of("total_employees", findEmployeeNumber(),
                "total_clients", findClientVendorNumber(),
                "total_products", findProductNumber(),
                "total_products_sold", findSumQtyOfSoldProducts());
    }

    private int findSumQtyOfSoldProducts() {
        Integer count = invoiceProductService.sumQuantityOfSoldProducts();
        if (count == null) {
            return 0;
        }
        return count;
    }

    private int findProductNumber() {
        List<ProductDTO> products = productService.findAll();
        if (products == null || products.isEmpty()) {
            return 0;
        }
        return products.size();
    }

    private int findClientVendorNumber() {
        List<ClientVendorDTO> clientVendors = clientVendorService.findAll();
        if (clientVendors == null || clientVendors.isEmpty()) {
            return 0;
        }
        return clientVendors.size();
    }

    private int findEmployeeNumber() {
        List<UserDTO> employees = userService.findAll();
        if (employees == null || employees.isEmpty()) {
            return 0;
        }
        return employees.size();
    }

    public Map<String, BigDecimal> getFinancialSummaryBasedOnCurrentSales(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, List<InvoiceDTO>> invoices = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoiceByCurrency = invoices.get(Currency.valueOf(currency));

        if (invoiceByCurrency == null)
            return new HashMap<>();

        BigDecimal totalCostBasedOnCurrentSales = findTotalCostBasedOnCurrentSales(invoiceByCurrency);
        BigDecimal profitLossBasedOnCurrentSales = findProfitLossBasedOnCurrentSales(invoiceByCurrency);

        return Map.of(
                "total_cost", totalCostBasedOnCurrentSales,
                "total_sales", sumTotalSalesByDateInOneCurrency(year, startMonth, endMonth, currency),
                "profit_loss", profitLossBasedOnCurrentSales
        );

    }

    private BigDecimal findTotalCostBasedOnCurrentSales(List<InvoiceDTO> approveInvoices) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (InvoiceDTO each : approveInvoices) {
            List<InvoiceProductDTO> ip = invoiceProductService.findAllByInvoiceId(each.getId());
            for (InvoiceProductDTO each1 : ip) {
                totalCost = totalCost.add(each1.getProduct().getPrice().multiply(BigDecimal.valueOf(each1.getQuantity())));
            }
        }
        return totalCost;
    }

    private BigDecimal findProfitLossBasedOnCurrentSales(List<InvoiceDTO> approveInvoices) {
        BigDecimal profitLoss = BigDecimal.ZERO;
        for (InvoiceDTO each : approveInvoices) {
            List<InvoiceProductDTO> ip = invoiceProductService.findAllByInvoiceId(each.getId());
            for (InvoiceProductDTO each1 : ip) {
                profitLoss = profitLoss.add(each1.getProfitLoss());
            }
        }
        return profitLoss;
    }



    @Override
    public Map<String, BigDecimal> getFinancialSummaryInSeparateCurrency(int year, int startMonth, int endMonth, String currency) {

        Map<String, BigDecimal> map = new HashMap<>();

        map.put("total_cost", sumTotalCostByDateInOneCurrency(year, startMonth, endMonth, currency));
        map.put("total_sales", sumTotalSalesByDateInOneCurrency(year, startMonth, endMonth, currency));
        map.put("total_profit_loss", sumProfitLossByDateInOneCurrency(year, startMonth, endMonth, currency));

        return map;
    }


    @Override
    public Map<String, BigDecimal> getFinancialSummaryConvertedInOneCurrency(int year, int startMonth, int endMonth, String currency) {

        Map<String, BigDecimal> map = new HashMap<>();

        map.put("total_cost", sumTotalCostOfEachCurrencyInPeriodAndConvert(year, startMonth, endMonth, currency));
        map.put("total_sales", sumTotalSalesOfEachCurrencyInPeriodAndConvert(year, startMonth, endMonth, currency));
        map.put("total_profit_loss", sumTotalProfitLossEachCurrencyInPeriodAndConvert(year, startMonth, endMonth, currency));

        return map;
    }


    @Override
    public BigDecimal sumTotalCostOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, BigDecimal> map = new HashMap<>();

        for (Currency value : Currency.values()) {
            BigDecimal totalCostInEachCurrency = sumTotalCostByDateInOneCurrency(year, startMonth, endMonth, value.name());
            map.put(value, totalCostInEachCurrency);
        }

        BigDecimal totalCostUsd = map.get(Currency.USD);
        BigDecimal totalCostGel = map.get(Currency.GEL);
        BigDecimal totalCostEur = map.get(Currency.EUR);

        return calculateTotalCostOrTotalSalesOrTotalProfitLoss(totalCostUsd, totalCostGel, totalCostEur, currency);
    }

    @Override
    public BigDecimal sumTotalSalesOfEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, BigDecimal> map = new HashMap<>();

        for (Currency value : Currency.values()) {
            BigDecimal totalSalesInEachCurrency = sumTotalSalesByDateInOneCurrency(year, startMonth, endMonth, value.name());
            map.put(value, totalSalesInEachCurrency);
        }

        BigDecimal totalSalesUsd = map.get(Currency.USD);
        BigDecimal totalSalesGel = map.get(Currency.GEL);
        BigDecimal totalSalesEur = map.get(Currency.EUR);

        return calculateTotalCostOrTotalSalesOrTotalProfitLoss(totalSalesUsd, totalSalesGel, totalSalesEur, currency);
    }

    @Override
    public BigDecimal sumTotalProfitLossEachCurrencyInPeriodAndConvert(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, BigDecimal> map = new HashMap<>();

        for (Currency value : Currency.values()) {
            BigDecimal totalProfitLossInEachCurrency = sumProfitLossByDateInOneCurrency(year, startMonth, endMonth, value.name());
            map.put(value, totalProfitLossInEachCurrency);
        }

        BigDecimal totalProfitLossUSd = map.get(Currency.USD);
        BigDecimal totalProfitLossGel = map.get(Currency.GEL);
        BigDecimal totalProfitLossEur = map.get(Currency.EUR);

        return calculateTotalCostOrTotalSalesOrTotalProfitLoss(totalProfitLossUSd, totalProfitLossGel, totalProfitLossEur, currency);
    }

    @Override
    public BigDecimal sumTotalCostByDateInOneCurrency(int year, int startMonth, int endMonth, String currency) {

        BigDecimal totalCost = BigDecimal.ZERO;
        for (ProductDTO each : productService.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency)) {
            BigDecimal price = each.getPrice();
            int quantity = each.getQuantityInStock();
            BigDecimal cost = price.multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(cost);
        }

        return BigDecimalUtil.format(totalCost);
    }

    @Override
    public BigDecimal sumTotalSalesByDateInOneCurrency(int year, int startMonth, int endMonth, String currency) {
        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoicesByCurrency = map.get(Currency.valueOf(currency));
        if (invoicesByCurrency != null) {
            return invoicesByCurrency.stream()
                    .map(InvoiceDTO::getTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal sumProfitLossByDateInOneCurrency(int year, int startMonth, int endMonth, String currency) {
        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoicesByCurrency = map.get(Currency.valueOf(currency));
        if (invoicesByCurrency != null) {
            return invoicesByCurrency.stream()
                    .map(invoiceDTO -> invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO.getId())
                            .stream()
                            .map(InvoiceProductDTO::getProfitLoss).reduce(BigDecimal.ZERO, BigDecimal::add))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }


    private BigDecimal calculateTotalCostOrTotalSalesOrTotalProfitLoss(BigDecimal totalUsd, BigDecimal totalGel, BigDecimal totalEur, String currency) {

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
                throw new DataIsNotPresentException("Data is not present for " + currency);

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

}
