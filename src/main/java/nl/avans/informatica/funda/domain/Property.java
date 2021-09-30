package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Property {

    @Id
    @GeneratedValue
    private int id;

    private String address;
    private Integer askingPrice;

    public Property() {

    }

    public Property(String address, Integer askingPrice) {
        this.address = address;
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

    public Integer getAskingPrice() {
        return askingPrice;
    }

    public List<String> getChecklist() {
        List<String> result = new ArrayList<>();
        result.add("Is it pretty?");
        result.add("Is it well maintained?");
        return result;
    }

    public void printChecklist() {
        getChecklist().stream()
                .map(check -> "n. " + check)
                .forEach(System.out::println);
    }

    public abstract int getMonthlyPayment();
}
