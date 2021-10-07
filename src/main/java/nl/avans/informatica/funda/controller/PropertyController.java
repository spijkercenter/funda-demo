package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.dto.PropertyDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/properties")
public class PropertyController /* implements CanRead<Property, PropertyDto> */ {

    private final AbstractController<Property, PropertyDto> innerController;
    private final PropertyRepository propertyRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.innerController = new AbstractController<>(logger, propertyRepository) {
            @Override
            protected PropertyDto fromEntityToDto(Property property) {
                return new PropertyDto(
                        property.getId(),
                        property.getType(),
                        property.getAddress(),
                        property.getAskingPrice(),
                        property.getMonthlyPayment(),
                        property.getBids().stream().mapToInt(Bid::getId).boxed().collect(Collectors.toList()),
                        property.getChecklist()
                );
            }

            @Override
            protected Property fromDtoToEntity(PropertyDto dto) {
                throw new UnsupportedOperationException("Use specific controllers instead!");
            }
        };
    }

    @GetMapping
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Property getById(@PathVariable Integer id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Property create(@RequestBody Property input) {
        return propertyRepository.save(input);
    }

}
