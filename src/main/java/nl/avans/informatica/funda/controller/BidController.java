package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.BidRepository;
import nl.avans.informatica.funda.repository.CustomerRepository;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bids")
public class BidController {

    private final CustomerRepository customerRepository;
    private final BidRepository bidRepository;
    private final PropertyRepository propertyRepository;

    public BidController(CustomerRepository customerRepository, BidRepository bidRepository, PropertyRepository propertyRepository) {
        this.customerRepository = customerRepository;
        this.bidRepository = bidRepository;
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public Iterable<Bid> getAll() {
        return bidRepository.findAll();
    }

    @PostMapping
    public Bid doOffer(
            @RequestParam int customerId,
            @RequestParam int propertyId,
            @RequestParam int priceOffered
    ) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(IllegalArgumentException::new);
        Property property = propertyRepository.findById(propertyId).orElseThrow(IllegalArgumentException::new);

        Bid bid = property.doOffer(customer, priceOffered);
        if (bid != null) {
            bidRepository.save(bid);
            propertyRepository.save(property);
        }

        return bid;
    }
}
