package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CanRead;
import nl.avans.informatica.funda.controller.dto.PropertyDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/properties")
public class PropertyController implements CanRead<Property, PropertyDto> {

    private final AbstractController<Property, PropertyDto> innerController;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PropertyController(PropertyRepository propertyRepository) {
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
    public ResponseEntity<List<PropertyDto>> getAll() {
       return innerController.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getById(@PathVariable Integer id) {
        return innerController.getById(id);
    }

}
