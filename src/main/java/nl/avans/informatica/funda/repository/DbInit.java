package nl.avans.informatica.funda.repository;

import nl.avans.informatica.funda.domain.Apartment;
import nl.avans.informatica.funda.domain.House;
import nl.avans.informatica.funda.domain.Property;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    public DbInit(PropertyRepository propertyRepository) {
        Property house = new House("2",  5000, 100);
        Property apartment = new Apartment("34", 4000, 200, 2);

        propertyRepository.save(house);
        propertyRepository.save(apartment);
    }

}
