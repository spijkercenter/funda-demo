package nl.avans.informatica.funda.domain;

import java.util.ArrayList;
import java.util.List;

public class Garage extends Property {
    private boolean hasElectricity;

    public Garage(String address, Integer askingPrice, boolean hasElectricity) {
        super(address, askingPrice);
        this.hasElectricity = hasElectricity;
    }

    public Garage() {

    }

    public boolean hasElectricity() {
        return hasElectricity;
    }

    @Override
    public List<String> getChecklist() {
        List<String> result = new ArrayList<>();
        if (hasElectricity) {
            result.add("Has three-phase power?");
        }
        return result;
    }

    @Override
    public int getMonthlyPayment() {
        return 300;
    }
}
