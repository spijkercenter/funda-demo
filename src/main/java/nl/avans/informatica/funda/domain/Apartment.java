package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;

@Entity
public class Apartment extends Property {

    public static final String TYPE = "APARTMENT";

    private int paymentHoa;
    private int floor;

    public Apartment(String address, Integer askingPrice, int paymentHoa, int floor) {
        super(address, askingPrice);
        this.paymentHoa = paymentHoa;
        this.floor = floor;
    }

    public Apartment() {

    }

    @Override
    public int getMonthlyPayment() {
        return floor * getAskingPrice();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public int getPaymentHoa() {
        return paymentHoa;
    }

    public int getFloor() {
        return floor;
    }
}
