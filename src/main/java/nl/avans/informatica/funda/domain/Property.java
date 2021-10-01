package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public abstract class Property implements HasModifiableId {

    @Id
    @GeneratedValue
    private int id;

    private String address;
    private Integer askingPrice;

    @OneToMany
    private List<Bid> bids;

    public Property() {

    }

    public Property(String address, Integer askingPrice) {
        this.address = address;
        this.askingPrice = askingPrice;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public List<Bid> getBids() {
        return Objects.requireNonNullElse(Collections.unmodifiableList(bids), Collections.emptyList());
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

    public String getType() {
        return getClass().getSimpleName();
    }
}
