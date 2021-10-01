package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Bid implements HasModifiableId {

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
        if (customer == null || property == null) {
            throw new IllegalArgumentException();
        }
        this.priceOffered = priceOffered;
        this.customer = customer;
        this.property = property;
        this.timeOfBid = LocalDateTime.now();
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
