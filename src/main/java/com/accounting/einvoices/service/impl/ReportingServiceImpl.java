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

import javax.naming.directory.InvalidAttributesException;
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
    public Map<String, BigDecimal> getFinancialSummaryByEachCurrency(int year, int startMonth, int endMonth, String currency) {

        Map<String, BigDecimal> map = new HashMap<>();

        map.put("total_cost", countTotalCostByDate(year, startMonth, endMonth, currency));
        map.put("total_sales", countTotalSalesByDate(year, startMonth, endMonth, currency));
        map.put("total_profit_loss", sumProfitLossByDate(year, startMonth, endMonth, currency));

        return map;
    }





    @Override
    public Map<String, BigDecimal> getFinancialSummaryInOneCurrency(int year, int startMonth, int endMonth, String currency) {
        return Map.of();
    }


    @Override
    public BigDecimal sumTotalCostsByCurrencyForPeriodAndConvert(int year, int startMonth, int endMonth, String currency) throws InvalidAttributesException {

        BigDecimal totalCost = BigDecimal.ZERO;

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


        CurrencyExchangeDTO usd = dashboardService.exchangeRatesOf("USD", 1L);
        CurrencyExchangeDTO gel = dashboardService.exchangeRatesOf("GEL", 1L);
        CurrencyExchangeDTO eur = dashboardService.exchangeRatesOf("EUR", 1L);

        BigDecimal totalUsd = map.get(Currency.USD);
        BigDecimal totalGel = map.get(Currency.GEL);
        BigDecimal totalEur = map.get(Currency.EUR);


        switch (currency) {
            case "USD":
                BigDecimal gelToUsd = gel.getUsd();
                BigDecimal eurToUsd = eur.getUsd();

                totalCost = totalCost.add(totalUsd).add(totalGel.multiply(gelToUsd)).add(totalEur.multiply(eurToUsd));
                break;
            case "GEL":
                BigDecimal usdToGel = usd.getGel();
                BigDecimal eurToGel = eur.getGel();

                totalCost = totalCost.add(totalGel).add(totalUsd.multiply(usdToGel)).add(totalEur.multiply(eurToGel));
                break;
            case "EUR":
                BigDecimal usdToEur = usd.getEur();
                BigDecimal gelToEur = gel.getEur();

                totalCost = totalCost.add(totalEur).add(totalUsd.multiply(usdToEur)).add(totalGel.multiply(gelToEur));
                break;
            default:
                throw new InvalidAttributesException("Invalid currency code!");
        }


        return BigDecimalUtil.format(totalCost);
    }




    private BigDecimal countTotalCostByDate(int year, int startMonth, int endMonth, String currency) {

        BigDecimal totalCost = BigDecimal.ZERO;
        for (ProductDTO each : productService.findAllByCreatedDateBetweenMonths(year, startMonth, endMonth, currency)) {
            BigDecimal price = each.getPrice();
            int quantity = each.getQuantityInStock();
            BigDecimal cost = price.multiply(BigDecimal.valueOf(quantity));
            totalCost = totalCost.add(cost);
        }
        return BigDecimalUtil.format(totalCost);
    }

    private BigDecimal countTotalSalesByDate(int year, int startMonth, int endMonth, String currency) {
        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoicesByCurrency = map.get(Currency.valueOf(currency));
        return invoicesByCurrency.stream()
                .map(InvoiceDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private BigDecimal sumProfitLossByDate(int year, int startMonth, int endMonth, String currency) {
        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);
        List<InvoiceDTO> invoicesByCurrency = map.get(Currency.valueOf(currency));
        return invoicesByCurrency.stream()
                .map(invoiceDTO -> invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO.getId())
                        .stream().map(InvoiceProductDTO::getProfitLoss).reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
