package nl.avans.informatica.funda;

import nl.avans.informatica.funda.domain.Apartment;
import nl.avans.informatica.funda.domain.House;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.domain.PropertyMarket;
import org.springframework.stereotype.Repository;

import javax.el.PropertyNotFoundException;
import java.util.List;

@Repository
public class BullshitRepository {

    private PropertyMarket market;

    public BullshitRepository() {
        market = new PropertyMarket();

        Property house = new House("2",  5000, 100);
        Property apartment = new Apartment("34", 4000, 200, 2);

        market.addProperty(house);
        market.addProperty(apartment);
    }

    public List<Property> getAll() {
        return market.getAll();
    }

    public void addProperty(Property property) {
        market.addProperty(property);
    }

    public PropertyMarket getMarket() {
        return market;
    }

    public Property getByAddress(String address) {
        return market.getAll().stream()
                .filter(p -> p.getAddress().equals(address))
                .findFirst()
                .orElseThrow(PropertyNotFoundException::new);

    }
}
