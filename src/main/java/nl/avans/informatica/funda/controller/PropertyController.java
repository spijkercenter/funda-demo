package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.BullshitRepository;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repositories.PropertyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public Iterable<Property> getAll(
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        if (minPrice == null || maxPrice == null) {
            return propertyRepository.findAll();
        }
        return propertyRepository.findAllByAskingPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/{address}")
    public Property getByAddress(
            BullshitRepository bullshitRepository,
            @PathVariable String address) {
        return bullshitRepository.getByAddress(address);
    }

    @PostMapping
    public Property createProperty(
            BullshitRepository bullshitRepository,
            @RequestBody Property property) {
        if (property.getAskingPrice() == null) {
            throw new IllegalArgumentException();
        }
        bullshitRepository.addProperty(property);
        return property;
    }
}
