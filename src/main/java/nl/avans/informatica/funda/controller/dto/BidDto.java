package nl.avans.informatica.funda.controller.dto;

import java.time.LocalDateTime;

public class BidDto {

    private Integer id;
    private int customerId;
    private int propertyId;
    private int priceOffered;
    private LocalDateTime timeOfBid;


    public Integer getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    public LocalDateTime getTimeOfBid() {
        return timeOfBid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setPriceOffered(int priceOffered) {
        this.priceOffered = priceOffered;
    }

    public void setTimeOfBid(LocalDateTime timeOfBid) {
        this.timeOfBid = timeOfBid;
    }
}
