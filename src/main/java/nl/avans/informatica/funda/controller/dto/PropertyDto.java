package nl.avans.informatica.funda.controller.dto;

import nl.avans.informatica.funda.controller.HasId;

import java.util.List;

public class PropertyDto implements HasId {
    private final Integer id;
    private final String type;
    private final String address;
    private final Integer askingPrice;
    private final Integer monthlyPayment;
    private final List<Integer> bidIds;
    private final List<String> checkList;

    public PropertyDto(Integer id, String type, String address, Integer askingPrice, Integer monthlyPayment, List<Integer> bidIds, List<String> checkList) {
        this.id = id;
        this.type = type;
        this.address = address;
        this.askingPrice = askingPrice;
        this.monthlyPayment = monthlyPayment;
        this.bidIds = bidIds;
        this.checkList = checkList;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAskingPrice() {
        return askingPrice;
    }

    public Integer getMonthlyPayment() {
        return monthlyPayment;
    }

    public List<Integer> getBidIds() {
        return bidIds;
    }

    public List<String> getCheckList() {
        return checkList;
    }
}
