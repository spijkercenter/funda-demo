package nl.avans.informatica.funda.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Property {
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
        List<String> checklist = getChecklist();
        for (int i = 0; i < checklist.size(); i++) {
            String check = checklist.get(i);
            System.out.println(i + ". " + check);
        }
    }

    public abstract int getMonthlyPayment();
}
