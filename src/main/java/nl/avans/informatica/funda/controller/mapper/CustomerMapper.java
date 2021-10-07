package nl.avans.informatica.funda.controller.mapper;

import nl.avans.informatica.funda.controller.dto.CustomerDto;
import nl.avans.informatica.funda.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {
    @Override
    public CustomerDto fromEntityToDto(Customer entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Customer fromDtoToEntity(CustomerDto dto) {
        Customer entity = new Customer(dto.getName(), dto.getEmail());
        Optional.ofNullable(dto.getId()).ifPresent(entity::setId);
        return entity;
    }
}
