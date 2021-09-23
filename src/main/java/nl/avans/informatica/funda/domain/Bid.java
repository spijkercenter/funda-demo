package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int priceOffered;

    @ManyToOne
    private Property property;

    @ManyToOne
    private Customer customer;
    private LocalDateTime timeOfBid;

    public Bid() {
    }

    public Bid(int priceOffered, Property property, Customer customer) {
        this.priceOffered = priceOffered;
        this.property = property;
        this.customer = customer;
        this.timeOfBid = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    public LocalDateTime getTimeOfBid() {
        return timeOfBid;
    }
}
