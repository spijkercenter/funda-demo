package nl.avans.informatica.funda.repositories;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Init {

    private final PropertyRepository propertyRepository;
    private final CustomerRepository customerRepository;
    private final BidRepository bidRepository;


    public Init(
            PropertyRepository propertyRepository,
            CustomerRepository customerRepository,
            BidRepository bidRepository
    ) {
        this.propertyRepository = propertyRepository;
        this.customerRepository = customerRepository;
        this.bidRepository = bidRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDb(
    ) {
        Property daneSt = new Property("1091 Dane St", 5000);
        Property oakLawnAve = new Property("3952 Oak Lawn Ave", 4000);
        propertyRepository.save(daneSt);
        propertyRepository.save(oakLawnAve);

        Customer ashley = new Customer("Ashley Walters", "ashley.walters@example.com");
        Customer deanna = new Customer("Deanna Owens", "deanna.owens@example.com");
        customerRepository.save(ashley);
        customerRepository.save(deanna);


        Bid bid1 = daneSt.doOffer(ashley, 6000);
        bidRepository.save(bid1);


    }

}
