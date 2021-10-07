package nl.avans.informatica.funda.service;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public void printChecklist(Property property) {
        property.getChecklist().stream()
                .map(check -> "n. " + check)
                .forEach(System.out::println);
    }

    public boolean isAccepted(Property property, Integer offerPrice) {
        if (offerPrice == null) {
            return false;
        }
        if (property.getAskingPrice() == null) {
            return false;
        }
        return offerPrice > property.getAskingPrice();
    }

    public Bid doOffer(Property property, Customer customer, Integer offerPrice) {
        if (isAccepted(property, offerPrice)) {
            Bid bid = new Bid(offerPrice, customer, property);
            property.addBid(bid);
            return bid;
        } else {
            return null;
        }
    }

    public Optional<Property> findById(int propertyId) {
        return propertyRepository.findById(propertyId);
    }

    public Property save(Property property) {
        return propertyRepository.save(property);
    }
}
