package nl.avans.informatica.funda.controller.dto;

import java.util.List;

public class ApartmentDto extends PropertyDto {

    private final Integer paymentHoa;
    private final Integer floor;

    public ApartmentDto(Integer id, String type, String address, Integer askingPrice, Integer monthlyPayment, List<Integer> bidIds, List<String> checkList, Integer paymentHoa, Integer floor) {
        super(id, type, address, askingPrice, monthlyPayment, bidIds, checkList);
        this.paymentHoa = paymentHoa;
        this.floor = floor;
    }

    public Integer getPaymentHoa() {
        return paymentHoa;
    }

    public Integer getFloor() {
        return floor;
    }
}
