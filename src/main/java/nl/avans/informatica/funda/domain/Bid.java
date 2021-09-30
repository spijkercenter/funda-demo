package nl.avans.informatica.funda.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private int id;

    private int priceOffered;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Property property;

    private LocalDateTime timeOfBid;

    public Bid() {

    }

    public Bid(int priceOffered, Customer customer, Property property) {
        this.priceOffered = priceOffered;
        this.customer = customer;
        this.property = property;
        this.timeOfBid = LocalDateTime.now();
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimeOfBid() {
        return timeOfBid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Property getProperty() {
        return property;
    }
}
