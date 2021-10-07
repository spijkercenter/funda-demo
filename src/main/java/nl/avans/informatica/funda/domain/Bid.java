package nl.avans.informatica.funda.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Bid {

    @Id
    @GeneratedValue
    private int id;

    private int priceOffered;

    @ManyToOne
    private Customer customer;

    @JsonManagedReference
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

    public int getId() {
        return id;
    }

    public int getPriceOffered() {
        return priceOffered;
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
