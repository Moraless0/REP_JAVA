package com.techfix.monolito;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {
    private Locale locale;

    public PriceFormatter(Locale locale) {
        this.locale = locale;
    }

    public String formatPrice(BigDecimal price) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(price);
    }

    public String formatPercentage(BigDecimal percentage) {
        return percentage + "%";
    }
}
