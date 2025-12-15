package com.example.logistics_app.models; // CHECK PACKAGE NAME

public class DeliveryOrder {
    private String orderId;
    private String fromLocation;
    private String toLocation;
    private String estimatedArrival;
    private boolean isSent;

    public DeliveryOrder(String orderId, String fromLocation, String toLocation, String estimatedArrival, boolean isSent) {
        this.orderId = orderId;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.estimatedArrival = estimatedArrival;
        this.isSent = isSent;
    }

    // Getters and Setter
    public String getOrderId() { return orderId; }
    public String getFromLocation() { return fromLocation; }
    public String getToLocation() { return toLocation; }
    public String getEstimatedArrival() { return estimatedArrival; }
    public boolean isSent() { return isSent; }
    public void setSent(boolean sent) { this.isSent = sent; }
}