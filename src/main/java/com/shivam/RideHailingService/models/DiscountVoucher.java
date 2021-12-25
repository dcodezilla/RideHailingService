package com.shivam.RideHailingService.models;

public class DiscountVoucher {
    private String voucherId;
    private Double MaxValue;
    private Double percentage;

    public DiscountVoucher(String voucherId, Double maxValue, Double percentage) {
        this.voucherId = voucherId;
        MaxValue = maxValue;
        this.percentage = percentage;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public Double getMaxValue() {
        return MaxValue;
    }

    public Double getPercentage() {
        return percentage;
    }
}
