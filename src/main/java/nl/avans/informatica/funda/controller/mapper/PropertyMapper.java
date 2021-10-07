package nl.avans.informatica.funda.controller.mapper;

import nl.avans.informatica.funda.controller.dto.PropertyDto;
import nl.avans.informatica.funda.controller.mapper.Mapper;
import nl.avans.informatica.funda.domain.Apartment;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Garage;
import nl.avans.informatica.funda.domain.House;
import nl.avans.informatica.funda.domain.Property;
import org.springframework.stereotype.Component;

import javax.validation.UnexpectedTypeException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PropertyMapper implements Mapper<Property, PropertyDto> {

    @Override
    public PropertyDto fromEntityToDto(Property entity) {
        PropertyDto dto = new PropertyDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setAddress(entity.getAddress());
        dto.setAskingPrice(entity.getAskingPrice());
        dto.setMonthlyPayment(entity.getMonthlyPayment());
        dto.setBids(entity.getBids().stream().map(Bid::getId).collect(Collectors.toList()));
        dto.setCheckList(entity.getChecklist());
        switch (entity.getType()) {
            case Apartment.TYPE:
                dto.setPaymentHoa(((Apartment) entity).getPaymentHoa());
                dto.setFloor(((Apartment) entity).getFloor());
                break;
            case Garage.TYPE:
                dto.setHasElectricity(((Garage)entity).hasElectricity());
                break;
            case House.TYPE:
                dto.setPlotArea(((House)entity).getPlotArea());
                break;
            default:
                throw new UnexpectedTypeException(entity.getType());
        }
        return dto;
    }

    @Override
    public Property fromDtoToEntity(PropertyDto dto) {
        final Property entity;
        switch (dto.getType()) {
            case Apartment.TYPE:
                entity = new Apartment(dto.getAddress(), dto.getAskingPrice(), dto.getPaymentHoa(), dto.getFloor());
                break;
            case Garage.TYPE:
                entity = new Garage(dto.getAddress(), dto.getAskingPrice(), dto.getHasElectricity());
                break;
            case House.TYPE:
                entity = new House(dto.getAddress(), dto.getAskingPrice(), dto.getPlotArea());
                break;
            default:
                throw new UnexpectedTypeException(dto.getType());
        }
        Optional.ofNullable(dto.getId()).ifPresent(entity::setId);
        return entity;
    }
}
