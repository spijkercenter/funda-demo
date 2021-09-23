package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String address;
    private int askingPrice;

    public Property() {
    }

    public Property(int id, String address, int askingPrice) {
        this.address = address;
        this.askingPrice = askingPrice;
    }

    public Property(String address, int askingPrice) {
        this.address = address;
        this.askingPrice = askingPrice;
    }

    public Bid doOffer(Customer customer, int offerPrice) {
        if (isAccepted(offerPrice)) {
            return new Bid(offerPrice, this, customer);
        } else {
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    private boolean isAccepted(int offerPrice) {
        return offerPrice > askingPrice;
    }

    public String getAddress() {
        return address;
    }

    public int getAskingPrice() {
        return askingPrice;
    }
}
