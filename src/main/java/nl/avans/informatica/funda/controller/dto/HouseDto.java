package nl.avans.informatica.funda.controller.dto;

import java.util.List;

public class HouseDto extends PropertyDto {
    private final Integer plotArea;

    public HouseDto(Integer id, String type, String address, Integer askingPrice, Integer monthlyPayment, List<Integer> bidIds, List<String> checkList, Integer plotArea) {
        super(id, type, address, askingPrice, monthlyPayment, bidIds, checkList);
        this.plotArea = plotArea;
    }

    public Integer getPlotArea() {
        return plotArea;
    }
}
