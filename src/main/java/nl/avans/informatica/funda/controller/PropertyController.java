package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public Iterable<Property> getAll(
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        if (minPrice == null && maxPrice == null) {
            return propertyRepository.findAll();
        }
        if (minPrice == null) {
            return propertyRepository.findByAskingPriceLessThan(maxPrice);
        }
        return propertyRepository.findByAskingPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/{address}")
    public Property getByAddress(
            @PathVariable String address) {
        return propertyRepository.findByAddress(address).orElse(null);
    }

    @PostMapping
    public Property createProperty(
            @RequestBody Property property) {
        if (property.getAskingPrice() == null) {
            throw new IllegalArgumentException();
        }
        propertyRepository.save(property);
        return property;
    }
}
