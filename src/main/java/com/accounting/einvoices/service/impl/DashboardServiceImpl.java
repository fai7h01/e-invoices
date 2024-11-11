package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    private final InvoiceService invoiceService;
    private final UserService userService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;
    private final InvoiceProductService invoiceProductService;

    public DashboardServiceImpl(InvoiceService invoiceService, UserService userService, ClientVendorService clientVendorService, ProductService productService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
    }

    @Override
    public Map<String, BigDecimal> summaryNumbers() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("total_cost", invoiceService.countTotalCost());
        map.put("total_sales", invoiceService.countTotalSales());
        map.put("total_profit_loss", invoiceService.sumProfitLoss());
        return map;
    }

    @Override
    public Map<String, Integer> totalProductsSoldEachDayMonth(String year, String month) {

        //maybe Pair.of to show month as well
        Map<String, Integer> soldProductsEachDay = new TreeMap<>();

        YearMonth date = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
        //calculate how many invoices are approved and how many invoice products are there in total

        int monthDays = date.lengthOfMonth();
        int firstDay = 1;

        while (firstDay <= monthDays) {

            LocalDate initialDate = LocalDate.of(date.getYear(), date.getMonthValue(), firstDay);

            List<InvoiceDTO> invoicesByDate = invoiceService.findAllByAcceptDate(initialDate);


            int finalFirstDay = firstDay;
            invoicesByDate.forEach(invoiceDTO -> {
                int count = invoiceProductService.findAllByInvoiceId(invoiceDTO.getId()).size();
                String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                Pair<String, Integer> pair = Pair.of(monthName, finalFirstDay);
                soldProductsEachDay.put(pair.getFirst() + " " + pair.getSecond(), count);
            });

            firstDay++;
        }

        log.info("products: {}", soldProductsEachDay);

        return soldProductsEachDay;
    }

    @Override
    public Map<String, Integer> summaryQuantities() {
        Map<String, Integer> map = new HashMap<>();
        map.put("total_employees", userService.findAll().size());
        map.put("total_clients", clientVendorService.findAll().size());
        map.put("total_products", productService.findAll().size());
        return map;
    }

}
