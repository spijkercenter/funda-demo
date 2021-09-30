package nl.avans.informatica.funda.domain;

import java.io.Serializable;

public class Property implements Serializable {
    private String address;
    private PropertyType propertyType;
    private Integer askingPrice;


    public Property() {

    }

    public Property(String address, PropertyType propertyType, Integer askingPrice) {
        this.address = address;
        this.propertyType = propertyType;
        this.askingPrice = askingPrice;
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

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public Integer getAskingPrice() {
        return askingPrice;
    }
}
