package nl.avans.informatica.funda.controller.dto;

import java.util.List;

public class PropertyDto {
    private Integer id;
    private String type;
    private String address;
    private Integer askingPrice;
    private Integer monthlyPayment;
    private List<Integer> bids;
    private List<String> checkList;
    private Integer paymentHoa;
    private Integer floor;
    private Boolean hasElectricity;
    private Integer plotArea;

    public PropertyDto() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAskingPrice(Integer askingPrice) {
        this.askingPrice = askingPrice;
    }

    public void setMonthlyPayment(Integer monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setBids(List<Integer> bidIds) {
        this.bids = bidIds;
    }

    public void setCheckList(List<String> checkList) {
        this.checkList = checkList;
    }

    public void setPaymentHoa(Integer paymentHoa) {
        this.paymentHoa = paymentHoa;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setHasElectricity(Boolean hasElectricity) {
        this.hasElectricity = hasElectricity;
    }

    public void setPlotArea(Integer plotArea) {
        this.plotArea = plotArea;
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

    public List<Integer> getBids() {
        return bids;
    }

    public List<String> getCheckList() {
        return checkList;
    }

    public Integer getPaymentHoa() {
        return paymentHoa;
    }

    public Integer getFloor() {
        return floor;
    }

    public Boolean getHasElectricity() {
        return hasElectricity;
    }

    public Integer getPlotArea() {
        return plotArea;
    }
}
