package com.accounting.einvoices.service.impl;

import com.accounting.einvoices.client.ExchangeRateClient;
import com.accounting.einvoices.dto.response.currencyExchange.ConversionRates;
import com.accounting.einvoices.dto.response.currencyExchange.CurrencyExchangeDTO;
import com.accounting.einvoices.dto.response.currencyExchange.ExchangeRateResponse;
import com.accounting.einvoices.exception.DataIsNotPresentException;
import com.accounting.einvoices.exception.exchangeRates.ExchangeRatesNotRetrievedException;
import com.accounting.einvoices.service.CurrencyExchangeService;
import com.accounting.einvoices.util.BigDecimalUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final ExchangeRateClient exchangeRateClient;
    private final CurrencyExchangeService currencyExchangeService;

    public CurrencyExchangeServiceImpl(ExchangeRateClient exchangeRateClient, @Lazy CurrencyExchangeService currencyExchangeService) {
        this.exchangeRateClient = exchangeRateClient;
        this.currencyExchangeService = currencyExchangeService;
    }


    @Override
    public Map<String, CurrencyExchangeDTO> getCurrencyExchangesMap() {
        CurrencyExchangeDTO usd = currencyExchangeService.exchangeRatesOf("USD", 1L);
        CurrencyExchangeDTO gel = currencyExchangeService.exchangeRatesOf("GEL", 1L);
        CurrencyExchangeDTO eur = currencyExchangeService.exchangeRatesOf("EUR", 1L);

        return Map.of(
                "USD", usd,
                "GEL", gel,
                "EUR", eur
        );

    }


    @Override
    public BigDecimal calculateTotalAndConvertToCommonCurrency(BigDecimal totalUsd, BigDecimal totalGel, BigDecimal totalEur, String currency) {

        BigDecimal total = BigDecimal.ZERO;

        Map<String, CurrencyExchangeDTO> currencyExchanges = getCurrencyExchangesMap();

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


    @Override
    public BigDecimal convertToCommonCurrency(BigDecimal total, String currentCurrency, String toCurrency) {

        Map<String, CurrencyExchangeDTO> currencyExchanges = getCurrencyExchangesMap();

        switch (toCurrency) {

            case "USD":

                BigDecimal usd = currencyExchanges.get(currentCurrency).getUsd();
                total = total.multiply(usd);

                break;
            case "GEL":

                BigDecimal gel = currencyExchanges.get(currentCurrency).getGel();
                total = total.multiply(gel);

                break;
            case "EUR":

                BigDecimal eur = currencyExchanges.get(currentCurrency).getEur();
                total = total.multiply(eur);

                break;
            default:
                throw new DataIsNotPresentException("Data is not present");
        }

        return BigDecimalUtil.format(total);
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
