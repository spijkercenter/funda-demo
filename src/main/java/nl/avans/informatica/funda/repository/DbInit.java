package nl.avans.informatica.funda.repository;

import nl.avans.informatica.funda.domain.Apartment;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.House;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.service.BidService;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    public DbInit(
            BidService bidService,
            CustomerRepository customerRepository,
            PropertyRepository propertyRepository
    ) {
        Property house = new House("2",  5000, 100);
        Property apartment = new Apartment("34", 4000, 200, 2);

        propertyRepository.save(house);
        propertyRepository.save(apartment);

        Customer customer = new Customer("Jan Modaal", "jan@modaal.nl");
        customerRepository.save(customer);

        Bid bid = new Bid(6000, customer, house);
        bidService.save(bid);
    }

}
