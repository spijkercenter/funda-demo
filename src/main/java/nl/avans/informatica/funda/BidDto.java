package nl.avans.informatica.funda;

import java.time.LocalDateTime;

public class BidDto {
    private int id;
    private int priceOffered;
    private int customerId;
    private int propertyId;
    private LocalDateTime timeOfBid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    public void setPriceOffered(int priceOffered) {
        this.priceOffered = priceOffered;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public LocalDateTime getTimeOfBid() {
        return timeOfBid;
    }

    public void setTimeOfBid(LocalDateTime timeOfBid) {
        this.timeOfBid = timeOfBid;
    }
}
