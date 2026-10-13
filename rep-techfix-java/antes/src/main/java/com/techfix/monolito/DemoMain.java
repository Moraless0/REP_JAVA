package com.techfix.monolito;

import java.util.Locale;

public class DemoMain {
    public static void main(String[] args) {
        System.out.println("=== DEMO VERSION ANTES (Violacion de REP) ===");
        System.out.println();

        PriceFormatter priceFormatter = new PriceFormatter(new Locale("es", "GT"));
        DeviceRepairService repairService = new DeviceRepairService(priceFormatter);

        System.out.println("1. Creando dispositivo con pantalla rota...");
        Device device = new Device("DEV001", "Apple", "MacBook Pro", Device.DeviceType.LAPTOP);

        System.out.println("2. Diagnosticando y cotizando reparacion...");
        RepairOrder order = repairService.diagnoseAndQuoteRepair(
            device,
            "Screen is cracked",
            "Juan Perez"
        );

        System.out.println("   - ID de Orden: " + order.getOrderId());
        System.out.println("   - Cliente: " + order.getCustomerName());
        System.out.println("   - Estado: " + order.getStatus());
        System.out.println("   - Costo Total: " + order.getTotalCost());
        System.out.println("   - Notas: " + order.getRepairNotes());
        System.out.println();

        System.out.println("=== DEMO: VIOLACION DE REP ===");
        System.out.println("Problema: DeviceRepairService depende de PriceFormatter");
        System.out.println("Si cambia el formato de precios (ej: de GTQ a USD),");
        System.out.println("DeviceRepairService debe recompilarse aunque solo use el calculo de costos");
        System.out.println("No hay versionado independiente - todo cambia junto");
    }
}
