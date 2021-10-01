package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CrudController;
import nl.avans.informatica.funda.controller.dto.CustomerDto;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
public class CustomerController implements CrudController<Customer, CustomerDto> {

    private final AbstractController<Customer, CustomerDto> innerController;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomerController(CustomerRepository customerRepository) {
        this.innerController = new AbstractController<>(logger, customerRepository) {
            @Override
            protected CustomerDto fromEntityToDto(Customer customer) {
                return new CustomerDto(customer.getId(), customer.getEmail(), customer.getName());
            }

            @Override
            protected Customer fromDtoToEntity(CustomerDto dto) {
                return new Customer(dto.getName(), dto.getEmail());
            }
        };
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return innerController.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Integer id) {
        return innerController.getById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto dto) {
        return innerController.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Integer id, @RequestBody CustomerDto dto) {
        return innerController.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        return innerController.deleteById(id);
    }
}
