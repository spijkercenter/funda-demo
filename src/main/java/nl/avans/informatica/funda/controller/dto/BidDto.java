package nl.avans.informatica.funda.controller.dto;

import nl.avans.informatica.funda.domain.Bid;

import java.time.LocalDateTime;

public class BidDto {

    private final int customerId;
    private final int propertyId;
    private final int priceOffered;
    private final LocalDateTime timeOfBid;

    public static BidDto of(Bid bid) {
        return new BidDto(
                bid.getCustomer().getId(),
                bid.getProperty().getId(),
                bid.getPriceOffered(),
                bid.getTimeOfBid()
        );
    }

    private BidDto(int customerId, int propertyId, int priceOffered, LocalDateTime timeOfBid) {
        this.customerId = customerId;
        this.propertyId = propertyId;
        this.priceOffered = priceOffered;
        this.timeOfBid = timeOfBid;
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
