package nl.avans.informatica.funda.controller.mapper;

import nl.avans.informatica.funda.controller.dto.BidDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.CustomerRepository;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BidMapper implements Mapper<Bid, BidDto> {

    private final PropertyRepository propertyRepository;
    private final CustomerRepository customerRepository;

    public BidMapper(PropertyRepository propertyRepository, CustomerRepository customerRepository) {
        this.propertyRepository = propertyRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public BidDto fromEntityToDto(Bid entity) {
        BidDto dto = new BidDto();
        dto.setId(entity.getId());
        dto.setPriceOffered(entity.getPriceOffered());
        dto.setPropertyId(entity.getProperty().getId());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setTimeOfBid(entity.getTimeOfBid());
        return dto;
    }

    @Override
    public Bid fromDtoToEntity(BidDto dto) {
        Customer customer = customerRepository.getById(dto.getCustomerId());
        Property property = propertyRepository.getById(dto.getPropertyId());

        Bid entity = new Bid(dto.getPriceOffered(), customer, property);
        Optional.ofNullable(dto.getId()).ifPresent(entity::setId);
        return entity;
    }
}
