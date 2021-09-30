package nl.avans.informatica.funda.domain;

public class Apartment extends Property {
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

    public int getPaymentHoa() {
        return paymentHoa;
    }

    public int getFloor() {
        return floor;
    }
}
