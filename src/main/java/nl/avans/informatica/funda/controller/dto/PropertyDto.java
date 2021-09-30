package nl.avans.informatica.funda.controller.dto;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Property;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyDto {
    private final String type;
    private final String address;
    private final Integer askingPrice;
    private final List<Integer> bidIds;

    public static PropertyDto of(Property property) {
        return new PropertyDto(
                property.getClass().getSimpleName(),
                property.getAddress(),
                property.getAskingPrice(),
                property.getBids().stream().mapToInt(Bid::getId).boxed().collect(Collectors.toList())
        );
    }

    private PropertyDto(String type, String address, Integer askingPrice, List<Integer> bidIds) {
        this.type = type;
        this.address = address;
        this.askingPrice = askingPrice;
        this.bidIds = bidIds;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAskingPrice() {
        return askingPrice;
    }

    public List<Integer> getBidIds() {
        return bidIds;
    }
}
