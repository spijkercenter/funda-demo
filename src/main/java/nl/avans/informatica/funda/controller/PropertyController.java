package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CrudController;
import nl.avans.informatica.funda.controller.dto.PropertyDto;
import nl.avans.informatica.funda.controller.mapper.PropertyMapper;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController implements CrudController<PropertyDto> {

    private final PropertyRepository propertyRepository;
    private final ControllerOperations<Property, PropertyDto> operations;

    public PropertyController(PropertyRepository propertyRepository, PropertyMapper mapper) {
        this.propertyRepository = propertyRepository;
        this.operations = new ControllerOperations<>(mapper);
    }

    @Override
    @GetMapping
    public List<PropertyDto> getAll() {
        return operations.getAll(propertyRepository::findAll);
    }

    @Override
    @GetMapping("/{id}")
    public PropertyDto getById(@PathVariable Integer id) {
        return operations.getById(id, propertyRepository::findById);
    }

    @Override
    @PostMapping
    public PropertyDto create(@RequestBody PropertyDto dto) {
        return operations.create(dto, propertyRepository::save);
    }

    @Override
    @DeleteMapping
    public void deleteById(Integer id) {
        operations.deleteById(id, propertyRepository::existsById, propertyRepository::deleteById);
    }

    @Override
    @PutMapping
    public PropertyDto update(Integer id, PropertyDto dto) {
        return operations.update(id, dto, propertyRepository::existsById, propertyRepository::save, Property::setId);
    }
}
