package com.techfix.monolito;

import java.math.BigDecimal;
import java.util.UUID;

public class DeviceRepairService {
    private final PriceFormatter priceFormatter;

    public DeviceRepairService(PriceFormatter priceFormatter) {
        this.priceFormatter = priceFormatter;
    }

    public RepairOrder diagnoseAndQuoteRepair(Device device, String issueDescription, String customerName) {
        String orderId = generateOrderId();
        RepairOrder order = new RepairOrder(orderId, device, issueDescription, customerName);

        BigDecimal cost = calculateRepairCost(device, issueDescription);
        order.setTotalCost(cost);
        order.addRepairNote("Costo formateado: " + priceFormatter.formatPrice(cost));

        order.setStatus(RepairOrder.RepairStatus.PENDING);

        return order;
    }

    private BigDecimal calculateRepairCost(Device device, String issueDescription) {
        String lowerIssue = issueDescription.toLowerCase();
        if (lowerIssue.contains("screen") || lowerIssue.contains("display")) {
            return new BigDecimal("1500.00");
        } else if (lowerIssue.contains("battery") || lowerIssue.contains("power")) {
            return new BigDecimal("600.00");
        } else if (lowerIssue.contains("water") || lowerIssue.contains("liquid")) {
            return new BigDecimal("2000.00");
        } else {
            return new BigDecimal("800.00");
        }
    }

    private String generateOrderId() {
        return "REP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
