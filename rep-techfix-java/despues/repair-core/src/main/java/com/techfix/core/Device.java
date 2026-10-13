package com.techfix.core;

public class Device {
    private String deviceId;
    private String brand;
    private String model;
    private DeviceType type;

    public Device(String deviceId, String brand, String model, DeviceType type) {
        this.deviceId = deviceId;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public DeviceType getType() {
        return type;
    }

    public enum DeviceType {
        LAPTOP, SMARTPHONE, TABLET
    }
}
