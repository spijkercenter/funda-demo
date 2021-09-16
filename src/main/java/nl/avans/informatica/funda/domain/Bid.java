package nl.avans.informatica.funda.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bid {
    private final int priceOffered;
    private final Customer customer;
    private final LocalDateTime timeOfBid;

    public Bid(int priceOffered, Customer customer) {
        this.priceOffered = priceOffered;
        this.customer = customer;
        this.timeOfBid = LocalDateTime.now();
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    public LocalDateTime getTimeOfBid() {
        return timeOfBid;
    }
}
