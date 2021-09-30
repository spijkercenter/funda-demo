package nl.avans.informatica.funda.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return properties.stream()
                .filter(p -> p.getAskingPrice() >= minPrice)
                .filter(p -> p.getAskingPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
