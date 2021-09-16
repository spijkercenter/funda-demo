package nl.avans.informatica.funda.domain;

import java.io.Serializable;

public class Property implements Serializable {
    private String address;
    // TODO enum
    private String propertyType;
    private Integer askingPrice;


    public Property() {

    }

    public Property(String address, String propertyType, Integer askingPrice) {
        this.address = address;
        this.propertyType = propertyType;
        this.askingPrice = askingPrice;
    }

    public Property(String address, String propertyType) {
        this(address, propertyType, null);
    }

    public Bid doOffer(Customer customer, Integer offerPrice) {
        if (isAccepted(offerPrice)) {
            return new Bid(offerPrice, customer);
        } else {
            return null;
        }
    }

    private boolean isAccepted(Integer offerPrice) {
        if (offerPrice == null) {
            return false;
        }
        if (askingPrice == null) {
            return false;
        }
        return offerPrice > askingPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public Integer getAskingPrice() {
        return askingPrice;
    }
}
