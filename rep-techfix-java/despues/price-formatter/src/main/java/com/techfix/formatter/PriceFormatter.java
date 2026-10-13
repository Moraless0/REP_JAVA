package com.techfix.formatter;

import com.techfix.core.RepairOrder;
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

    public String formatOrderPrice(RepairOrder order) {
        return formatPrice(order.getTotalCost());
    }
}
