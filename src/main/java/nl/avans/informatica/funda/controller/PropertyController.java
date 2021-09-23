package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repositories.PropertyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public Iterable<Property> getAll(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        if (address !=  null) {
            return propertyRepository.findAllByAddressEquals(address);
        }
        if (minPrice == null) {
            minPrice = 0;
        }
        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }
        return propertyRepository.findAllByAskingPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/{id}")
    public Optional<Property> getById(
            @PathVariable Integer id) {
        return propertyRepository.findById(id);
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        propertyRepository.save(property);
        return property;
    }
}
