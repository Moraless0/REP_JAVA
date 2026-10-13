package com.techfix.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RepairServiceTest {
    private DeviceRepairService repairService;

    @BeforeEach
    void setUp() {
        repairService = new DeviceRepairService();
    }

    @Test
    void testDiagnoseAndQuoteRepair_ScreenIssue() {
        Device device = new Device("DEV001", "Apple", "MacBook Pro", Device.DeviceType.LAPTOP);
        RepairOrder order = repairService.diagnoseAndQuoteRepair(
            device,
            "Screen is cracked",
            "Juan Perez"
        );

        assertNotNull(order);
        assertEquals("Juan Perez", order.getCustomerName());
        assertEquals(RepairOrder.RepairStatus.PENDING, order.getStatus());
        assertNotNull(order.getTotalCost());
        assertTrue(order.getTotalCost().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(order.getTotalCost().compareTo(new BigDecimal("1000.00")) > 0);
    }

    @Test
    void testDiagnoseAndQuoteRepair_BatteryIssue() {
        Device device = new Device("DEV002", "Samsung", "Galaxy S21", Device.DeviceType.SMARTPHONE);
        RepairOrder order = repairService.diagnoseAndQuoteRepair(
            device,
            "Battery drains quickly",
            "Maria Garcia"
        );

        assertNotNull(order);
        assertEquals("Maria Garcia", order.getCustomerName());
        assertEquals(RepairOrder.RepairStatus.PENDING, order.getStatus());
        assertTrue(order.getTotalCost().compareTo(new BigDecimal("500.00")) >= 0);
    }

    @Test
    void testModuleIndependence_CompilesWithoutFormatterDependency() {
        DeviceRepairService service = new DeviceRepairService();
        Device device = new Device("DEV003", "Dell", "XPS 15", Device.DeviceType.LAPTOP);

        RepairOrder order = service.diagnoseAndQuoteRepair(
            device,
            "Screen issue",
            "Carlos Lopez"
        );

        assertNotNull(order);
        assertNotNull(order.getOrderId());
        assertNotNull(order.getTotalCost());

        assertTrue(order.getTotalCost().compareTo(BigDecimal.ZERO) > 0);
    }
}
