package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.dto.PropertyDto;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public Iterable<PropertyDto> getAll(
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        final List<Property> result;
        if (minPrice == null && maxPrice == null) {
            result = propertyRepository.findAll();
        } else if (minPrice == null) {
            result = propertyRepository.findByAskingPriceLessThan(maxPrice);
        } else {
            result = propertyRepository.findByAskingPriceBetween(minPrice, maxPrice);
        }
        return result.stream().map(PropertyDto::of).collect(Collectors.toList());
    }

    @GetMapping("/{address}")
    public PropertyDto getByAddress(
            @PathVariable String address) {
        return PropertyDto.of(propertyRepository.findByAddress(address).orElse(null));
    }

    @PostMapping
    public PropertyDto createProperty(
            @RequestBody PropertyDto propertyDto) {
        // TODO
        throw new UnsupportedOperationException("TODO change into different controllers per implementation");
    }
}
