package nl.avans.informatica.funda.domain;

public class Garage extends Property {
    private boolean hasElectricity;

    public Garage(String address, Integer askingPrice, boolean hasElectricity) {
        super(address, askingPrice);
        this.hasElectricity = hasElectricity;
    }

    public Garage() {

    }

    public boolean isHasElectricity() {
        return hasElectricity;
    }
}
