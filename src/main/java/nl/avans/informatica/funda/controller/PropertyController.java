package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import nl.avans.informatica.funda.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;
    private final PropertyService propertyService;

    public PropertyController(
            PropertyRepository propertyRepository,
            PropertyService propertyService
    ) {
        this.propertyRepository = propertyRepository;
        this.propertyService = propertyService;
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
        return propertyRepository.findByAddress(address)
//                .orElse(null); // 200 code without result
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // 404 exception
    }


    // Return with ResponseEntity
    public ResponseEntity<Property> getByAddress2(
            @PathVariable String address) {
        return propertyRepository.findByAddress(address)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
