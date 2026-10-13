package com.techfix.monolito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class RepairServiceTest {
    private DeviceRepairService repairService;
    private PriceFormatter priceFormatter;

    @BeforeEach
    void setUp() {
        priceFormatter = new PriceFormatter(new Locale("es", "GT"));
        repairService = new DeviceRepairService(priceFormatter);
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
    void testCouplingViolation_ChangeInFormatterAffectsCoreLogic() {
        PriceFormatter usdFormatter = new PriceFormatter(Locale.US);
        DeviceRepairService newRepairService = new DeviceRepairService(usdFormatter);

        Device device = new Device("DEV003", "Dell", "XPS 15", Device.DeviceType.LAPTOP);
        RepairOrder order = newRepairService.diagnoseAndQuoteRepair(
            device,
            "Screen issue",
            "Carlos Lopez"
        );

        assertNotNull(order);
        assertEquals("Carlos Lopez", order.getCustomerName());

        String formattedPrice = usdFormatter.formatPrice(order.getTotalCost());
        assertTrue(formattedPrice.contains("$") || formattedPrice.contains("USD"));
    }
}
