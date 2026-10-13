package com.techfix.core;

public class DemoMain {
    public static void main(String[] args) {
        System.out.println("=== DEMO VERSION DESPUES (Aplicando REP) ===");
        System.out.println();

        DeviceRepairService repairService = new DeviceRepairService();

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

        System.out.println("=== DEMO: APLICACION DE REP ===");
        System.out.println("Beneficio: repair-core NO depende de PriceFormatter");
        System.out.println("repair-core puede compilar y ejecutarse de forma aislada");
        System.out.println("PriceFormatter esta en su propio modulo con versionado independiente");
        System.out.println("Si cambia el formato de precios, solo price-formatter necesita actualizarse");
        System.out.println("repair-core puede seguir en v1.0.0 mientras price-formatter pasa a v1.1.0");
    }
}
