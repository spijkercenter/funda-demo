package nl.avans.informatica.funda.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public abstract class Property {

    @Id
    @GeneratedValue
    private int id;

    private String address;
    private Integer askingPrice;

    @JsonBackReference
    @OneToMany
    private List<Bid> bids;

    public Property() {

    }

    public Property(String address, Integer askingPrice) {
        this.address = address;
        this.askingPrice = askingPrice;
        this.bids = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAskingPrice() {
        return askingPrice;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public List<String> getChecklist() {
        List<String> result = new ArrayList<>();
        result.add("Is it pretty?");
        result.add("Is it well maintained?");
        return result;
    }

    public abstract int getMonthlyPayment();
}
