package nl.avans.informatica.funda.repositories;

import nl.avans.informatica.funda.domain.Property;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Init {

    private PropertyRepository propertyRepository;

    public Init(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
        Property green = new Property("HOUSE", 5000);
        Property yellow = new Property("APARTMENT", 4000);
        propertyRepository.save(green);
        propertyRepository.save(yellow);
    }

}
