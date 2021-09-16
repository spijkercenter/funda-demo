package nl.avans.informatica.funda.domain;

import java.util.ArrayList;
import java.util.List;

public class PropertyMarket {
    private final List<Property> properties = new ArrayList<>();

    public void addProperty(Property home) {
        this.properties.add(home);
    }

    public List<Property> getAll() {
        return properties;
    }

    public List<Property> search(int maxPrice) {
        return search(0, maxPrice);
    }

    public List<Property> search(int minPrice, int maxPrice) {
        List<Property> result = new ArrayList<>();
        for (Property property : properties) {
            if (property.getAskingPrice() >= minPrice && property.getAskingPrice() <= maxPrice) {
                result.add(property);
            }
        }
        return result;
    }
}
