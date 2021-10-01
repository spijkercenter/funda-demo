package nl.avans.informatica.funda.controller.dto;

import nl.avans.informatica.funda.controller.HasId;

import java.time.LocalDateTime;

public class BidDto implements HasId {

    private final Integer id;
    private final int customerId;
    private final int propertyId;
    private final int priceOffered;
    private final LocalDateTime timeOfBid;

    public BidDto(int id, int customerId, int propertyId, int priceOffered, LocalDateTime timeOfBid) {
        this.id = id;
        this.customerId = customerId;
        this.propertyId = propertyId;
        this.priceOffered = priceOffered;
        this.timeOfBid = timeOfBid;
    }

    @Override
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
}
