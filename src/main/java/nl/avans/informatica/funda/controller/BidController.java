package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repositories.BidRepository;
import nl.avans.informatica.funda.repositories.CustomerRepository;
import nl.avans.informatica.funda.repositories.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/bids")
public class BidController {

    private final BidRepository bidRepository;
    private final PropertyRepository propertyRepository;
    private final CustomerRepository customerRepository;

    public BidController(
            BidRepository bidRepository,
            PropertyRepository propertyRepository,
            CustomerRepository customerRepository
    ) {
        this.bidRepository = bidRepository;
        this.propertyRepository = propertyRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public Iterable<Bid> getAll(
            @RequestParam(required = false) Integer propertyId
    ) {
        if (propertyId == null) {
            return bidRepository.findAll();
        } else {
            return bidRepository.findAllByProperty_Id(propertyId);
        }
    }

    @PostMapping
    public Bid makeOffer(
            @RequestParam Integer propertyId,
            @RequestParam Integer customerId,
            @RequestParam Integer priceOffered
    ) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(EntityNotFoundException::new);
        Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
        Bid bid =  property.doOffer(customer, priceOffered);
        if (bid != null) {
            bidRepository.save(bid);
        }
        return bid;
    }
}
