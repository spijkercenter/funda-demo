package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CrudController;
import nl.avans.informatica.funda.controller.dto.CustomerDto;
import nl.avans.informatica.funda.controller.mapper.CustomerMapper;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.repository.CustomerRepository;
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
@RequestMapping("/customers")
public class CustomerController implements CrudController<CustomerDto> {

    private final CustomerRepository customerRepository;
    private final ControllerOperations<Customer, CustomerDto> operations;

    public CustomerController(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.operations = new ControllerOperations<>(mapper);
    }

    @Override
    @GetMapping
    public List<CustomerDto> getAll() {
        return operations.getAll(customerRepository::findAll);
    }

    @Override
    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable Integer id) {
        return operations.getById(id, customerRepository::findById);
    }

    @Override
    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return operations.create(dto, customerRepository::save);
    }

    @Override
    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Integer id, @RequestBody CustomerDto dto) {
        return operations.update(id, dto, customerRepository::existsById, customerRepository::save, Customer::setId);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        operations.deleteById(id, customerRepository::existsById, customerRepository::deleteById);
    }
}
