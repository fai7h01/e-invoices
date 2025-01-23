package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.*;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private final CurrencyExchangeService currencyExchangeService;

    public ReportingServiceImpl(ProductService productService, InvoiceService invoiceService, InvoiceProductService invoiceProductService,
                                UserService userService, ClientVendorService clientVendorService, CurrencyExchangeService currencyExchangeService) {
        this.productService = productService;
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
        this.currencyExchangeService = currencyExchangeService;
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

    @Override
    public Map<String, BigDecimal> getFinancialSummaryBasedOnCurrentSales(int year, int startMonth, int endMonth, String currency) {

        Map<Currency, List<InvoiceDTO>> invoices = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);

        return findFinancialSummaryConvertedOneCurrency(invoices, currency);

    }

    private Map<String, BigDecimal> findFinancialSummaryConvertedOneCurrency(Map<Currency, List<InvoiceDTO>> invoices, String currency) {

        List<InvoiceDTO> invoicesUSD = invoices.get(Currency.valueOf("USD"));
        List<InvoiceDTO> invoicesEUR = invoices.get(Currency.valueOf("EUR"));
        List<InvoiceDTO> invoicesGEL = invoices.get(Currency.valueOf("GEL"));


        Map<String, BigDecimal> financialSummaryInUSD = invoicesUSD != null ? findFinancialSummaryInUSD(invoicesUSD) : Map.of(
                "total_cost_USD", BigDecimal.ZERO,
                "total_sales_USD", BigDecimal.ZERO,
                "profit_loss_USD", BigDecimal.ZERO
        );
        Map<String, BigDecimal> financialSummaryInEUR = invoicesEUR != null ? findFinancialSummaryInEUR(invoicesEUR) : Map.of(
                "total_cost_EUR", BigDecimal.ZERO,
                "total_sales_EUR", BigDecimal.ZERO,
                "profit_loss_EUR", BigDecimal.ZERO
        );
        Map<String, BigDecimal> financialSummaryInGEL = invoicesGEL != null ? findFinancialSummaryInGEL(invoicesGEL) : Map.of(
                "total_cost_GEL", BigDecimal.ZERO,
                "total_sales_GEL", BigDecimal.ZERO,
                "profit_loss_GEL", BigDecimal.ZERO
        );


        BigDecimal totalCostConverted =
                currencyExchangeService.calculateTotalAndConvertToCommonCurrency(
                        financialSummaryInUSD.get("total_cost_USD"),
                        financialSummaryInGEL.get("total_cost_GEL"),
                        financialSummaryInEUR.get("total_cost_EUR"), currency);
        BigDecimal totalSalesConverted =
                currencyExchangeService.calculateTotalAndConvertToCommonCurrency(
                        financialSummaryInUSD.get("total_sales_USD"),
                        financialSummaryInGEL.get("total_sales_GEL"),
                        financialSummaryInEUR.get("total_sales_EUR"), currency);
        BigDecimal profitLossConverted =
                currencyExchangeService.calculateTotalAndConvertToCommonCurrency(
                        financialSummaryInUSD.get("profit_loss_USD"),
                        financialSummaryInGEL.get("profit_loss_GEL"),
                        financialSummaryInEUR.get("profit_loss_EUR"), currency);

        return Map.of(
                "total_cost", totalCostConverted,
                "total_sales", totalSalesConverted,
                "profit_loss", profitLossConverted
        );
    }

    private Map<String, BigDecimal> findFinancialSummaryInEUR(List<InvoiceDTO> invoicesEUR) {
        BigDecimal totalCostEUR = findTotalCostBasedOnCurrentSales(invoicesEUR);
        BigDecimal totalSalesEUR = findTotalSalesBasedOnCurrentSales(invoicesEUR);
        BigDecimal profitLossEUR = findProfitLossBasedOnCurrentSales(invoicesEUR);
        return Map.of(
                "total_cost_EUR", totalCostEUR,
                "total_sales_EUR", totalSalesEUR,
                "profit_loss_EUR", profitLossEUR
        );
    }

    private Map<String, BigDecimal> findFinancialSummaryInGEL(List<InvoiceDTO> invoicesUSD) {
        BigDecimal totalCostGEL = findTotalCostBasedOnCurrentSales(invoicesUSD);
        BigDecimal totalSalesGEL = findTotalSalesBasedOnCurrentSales(invoicesUSD);
        BigDecimal profitLossGEL = findProfitLossBasedOnCurrentSales(invoicesUSD);
        return Map.of(
                "total_cost_USD", totalCostGEL,
                "total_sales_USD", totalSalesGEL,
                "profit_loss_USD", profitLossGEL
        );
    }

    private Map<String, BigDecimal> findFinancialSummaryInUSD(List<InvoiceDTO> invoicesUSD) {
        BigDecimal totalCostUSD = findTotalCostBasedOnCurrentSales(invoicesUSD);
        BigDecimal totalSalesUSD = findTotalSalesBasedOnCurrentSales(invoicesUSD);
        BigDecimal profitLossUSD = findProfitLossBasedOnCurrentSales(invoicesUSD);
        return Map.of(
                "total_cost_USD", totalCostUSD,
                "total_sales_USD", totalSalesUSD,
                "profit_loss_USD", profitLossUSD
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

    private BigDecimal findTotalSalesBasedOnCurrentSales(List<InvoiceDTO> invoiceByCurrency) {
        if (invoiceByCurrency != null) {
            return invoiceByCurrency.stream()
                    .map(InvoiceDTO::getTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
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


}
