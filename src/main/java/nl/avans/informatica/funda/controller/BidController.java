package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.BidRepository;
import nl.avans.informatica.funda.repository.CustomerRepository;
import nl.avans.informatica.funda.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bids")
public class BidController {

    private final CustomerRepository customerRepository;
    private final BidRepository bidRepository;
    private final PropertyService propertyService;

    public BidController(CustomerRepository customerRepository, BidRepository bidRepository, PropertyService propertyService) {
        this.customerRepository = customerRepository;
        this.bidRepository = bidRepository;
        this.propertyService = propertyService;
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
        Property property = propertyService.findById(propertyId).orElseThrow(IllegalArgumentException::new);

        Bid bid = propertyService.doOffer(property, customer, priceOffered);
        if (bid != null) {
            bidRepository.save(bid);
            propertyService.save(property);
            return bid;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
