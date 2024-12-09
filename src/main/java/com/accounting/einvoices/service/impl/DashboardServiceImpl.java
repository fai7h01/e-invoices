package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.annotation.ExecutionTime;
import com.accounting.einvoices.client.ExchangeRateClient;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.dto.response.ConversionRates;
import com.accounting.einvoices.dto.response.ExchangeRateResponse;
import com.accounting.einvoices.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

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

    @Override
    public Map<String, BigDecimal> summaryNumbers() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("total_cost", invoiceService.countTotalCost());
        map.put("total_sales", invoiceService.countTotalSales());
        map.put("total_profit_loss", invoiceService.sumProfitLoss());
        return map;
    }


    @ExecutionTime
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

            int eachDay = firstDay;
            log.info("\n\n >> EACH DAY: {}", eachDay);
            int count = 0;
            for (InvoiceDTO invoiceDTO : invoicesByDate) {

                count += invoiceProductService.findAllByInvoiceId(invoiceDTO.getId()).stream()
                        .map(InvoiceProductDTO::getQuantity)
                        .reduce(Integer::sum).orElse(0);
                log.info("Day of month and count of products: {}, {}", eachDay, count);
                String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                Pair<String, Integer> pair = Pair.of(monthName, eachDay);
                soldProductsEachDay.put(pair.getFirst() + " " + pair.getSecond(), count);
            }


            firstDay++;
        }

        log.info("products: {}", soldProductsEachDay);

        return soldProductsEachDay;
    }



    @Cacheable(value = "exchangeRates", key = "#code")
    @Override
    public Map<Pair<String, String>, String> exchangeRatePairs(String code) {

        Map<Pair<String, String>, String> rateMap = new HashMap<>();

        return switch (code) {
            case "GEL" -> getGelExchanges(rateMap, code);
            case "USD" -> getUsdExchanges(rateMap, code);
            case "EUR" -> getEuroExchanges(rateMap, code);
            default -> new HashMap<>();
        };
    }

    private Map<Pair<String, String>, String> getGelExchanges(Map<Pair<String, String>, String> rateMap, String code) {

        Map<String, ExchangeRateResponse> responseMap = exchangeRateResponsesMap();

        if (!code.equals("GEL")) return new HashMap<>();

        if (Objects.requireNonNull(responseMap.get("USD").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("USD").getConversionRates();
            Double forUsd = conversionRates.getGel();
            rateMap.put(Pair.of("USD", "GEL"), String.valueOf(forUsd));
        }

        if (Objects.requireNonNull(responseMap.get("EUR").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("EUR").getConversionRates();
            Double forEur = conversionRates.getGel();
            rateMap.put(Pair.of("EUR", "GEL"), String.valueOf(forEur));
        }

        if (Objects.requireNonNull(responseMap.get("GBP").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("GBP").getConversionRates();
            Double forGbp = conversionRates.getGel();
            rateMap.put(Pair.of("GBP", "GEL"), String.valueOf(forGbp));
        }

        if (Objects.requireNonNull(responseMap.get("CNY").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("CNY").getConversionRates();
            Double forCny = conversionRates.getGel();
            rateMap.put(Pair.of("CNY", "GEL"), String.valueOf(forCny));
        }

        return rateMap;

    }

    private Map<Pair<String, String>, String> getEuroExchanges(Map<Pair<String, String>, String> rateMap, String code) {

        Map<String, ExchangeRateResponse> responseMap = exchangeRateResponsesMap();

        if (!code.equals("EUR")) return new HashMap<>();

        if (Objects.requireNonNull(responseMap.get("EUR").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("EUR").getConversionRates();
            Double usd = conversionRates.getUsd();
            Double gbp = conversionRates.getGbp();
            Double cny = conversionRates.getCny();
            rateMap.put(Pair.of("EUR", "USD"), String.valueOf(usd));
            rateMap.put(Pair.of("EUR", "GBP"), String.valueOf(gbp));
            rateMap.put(Pair.of("EUR", "CNY"), String.valueOf(cny));
        }

        return rateMap;

    }

    private Map<Pair<String, String>, String> getUsdExchanges(Map<Pair<String, String>, String> rateMap, String code) {

        Map<String, ExchangeRateResponse> responseMap = exchangeRateResponsesMap();

        if (!code.equals("USD")) return new HashMap<>();

        if (Objects.requireNonNull(responseMap.get("USD").getResult()).equals("success")) {
            ConversionRates conversionRates = responseMap.get("USD").getConversionRates();
            Double eur = conversionRates.getEur();
            Double cny = conversionRates.getCny();
            Double gbp = conversionRates.getGbp();
            rateMap.put(Pair.of("USD", "EUR"), String.valueOf(eur));
            rateMap.put(Pair.of("USD", "CNY"), String.valueOf(cny));
            rateMap.put(Pair.of("USD", "GBP"), String.valueOf(gbp));
        }

        return rateMap;

    }

    private Map<String, ExchangeRateResponse> exchangeRateResponsesMap() {

        return Map.of(
                "USD", exchangeRateClient.getExchanges("USD"),
                "EUR", exchangeRateClient.getExchanges("EUR"),
                "GBP", exchangeRateClient.getExchanges("GBP"),
                "CNY", exchangeRateClient.getExchanges("CNY")
        );
    }

    @Override
    public Map<String, Integer> summaryQuantities() {
        Map<String, Integer> map = new HashMap<>();
        map.put("total_employees", userService.findAll().size());
        map.put("total_clients", clientVendorService.findAll().size());
        map.put("total_products", productService.findAll().size());
        map.put("total_products_sold", invoiceProductService.sumQuantityOfSoldProducts());
        return map;
    }

}
