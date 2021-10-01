package nl.avans.informatica.funda.controller.dto;


import java.util.List;

public class GarageDto extends PropertyDto {
    private final Boolean hasElectricity;

    public GarageDto(Integer id, String type, String address, Integer askingPrice, Integer monthlyPayment, List<Integer> bidIds, List<String> checkList, Boolean hasElectricity) {
        super(id, type, address, askingPrice, monthlyPayment, bidIds, checkList);
        this.hasElectricity = hasElectricity;
    }

    public Boolean getHasElectricity() {
        return hasElectricity;
    }
}
