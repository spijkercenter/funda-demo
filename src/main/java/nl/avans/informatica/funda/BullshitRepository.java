package nl.avans.informatica.funda;

import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.domain.PropertyMarket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BullshitRepository {

    private PropertyMarket market;

    public BullshitRepository() {
        market = new PropertyMarket();

        Property green = new Property(2, "HOUSE", 5000);
        Property yellow = new Property(34, "APARTMENT", 4000);

        market.addProperty(green);
        market.addProperty(yellow);
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
        for (Property property : market.getAll()) {
           if (property.getAddress().equals(address)) {
               return property;
           }
        }
        return null;
    }
}
