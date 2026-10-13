package com.techfix.monolito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RepairOrder {
    private String orderId;
    private Device device;
    private String issueDescription;
    private RepairStatus status;
    private BigDecimal totalCost;
    private String customerName;
    private List<String> repairNotes;

    public RepairOrder(String orderId, Device device, String issueDescription, String customerName) {
        this.orderId = orderId;
        this.device = device;
        this.issueDescription = issueDescription;
        this.customerName = customerName;
        this.status = RepairStatus.PENDING;
        this.repairNotes = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public Device getDevice() {
        return device;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getRepairNotes() {
        return repairNotes;
    }

    public void addRepairNote(String note) {
        this.repairNotes.add(note);
    }

    public enum RepairStatus {
        PENDING, IN_PROGRESS, COMPLETED
    }
}
