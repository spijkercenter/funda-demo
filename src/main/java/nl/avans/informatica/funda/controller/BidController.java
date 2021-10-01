package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CanCreate;
import nl.avans.informatica.funda.controller.archetypes.CanRead;
import nl.avans.informatica.funda.controller.dto.BidDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.CustomerRepository;
import nl.avans.informatica.funda.repository.PropertyRepository;
import nl.avans.informatica.funda.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController implements CanRead<Bid, BidDto>, CanCreate<Bid, BidDto> {

    private final AbstractController<Bid, BidDto> innerController;

    public BidController(BidService bidService, CustomerRepository customerRepository, PropertyRepository propertyRepository) {
        this.innerController = new AbstractController<>(bidService) {
            @Override
            protected BidDto fromEntityToDto(Bid b) {
                return new BidDto(
                        b.getId(),
                        b.getCustomer().getId(),
                        b.getProperty().getId(),
                        b.getPriceOffered(),
                        b.getTimeOfBid()
                );
            }

            @Override
            protected Bid fromDtoToEntity(BidDto dto) {
                Customer customer = customerRepository.getById(dto.getCustomerId());
                Property property = propertyRepository.getById(dto.getPropertyId());

                return new Bid(dto.getPriceOffered(), customer, property);
            }
        };
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BidDto>> getAll() {
        return innerController.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<BidDto> getById(@PathVariable Integer id) {
        return innerController.getById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<BidDto> create(@RequestBody BidDto dto) {
        return innerController.create(dto);
    }
}
