package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.dto.BidDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.BidRepository;
import nl.avans.informatica.funda.repository.CustomerRepository;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public BidDto doOffer(
            @RequestBody BidDto bidDto
    ) {
        Customer customer = customerRepository.findById(bidDto.getCustomerId()).orElseThrow(IllegalArgumentException::new);
        Property property = propertyRepository.findById(bidDto.getPropertyId()).orElseThrow(IllegalArgumentException::new);

        Bid bid = property.doOffer(customer, bidDto.getPriceOffered());
        if (bid != null) {
            bidRepository.save(bid);
            propertyRepository.save(property);
        }

        return BidDto.of(bid);
    }
}
