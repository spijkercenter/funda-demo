package nl.avans.informatica.funda.domain;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class House extends Property {

    public static final String TYPE = "HOUSE";

    private int plotArea;

    public House(String address, Integer askingPrice, int plotArea) {
        super(address, askingPrice);

        this.plotArea = plotArea;
    }

    public House() {
    }


    public int getPlotArea() {
        return plotArea;
    }

    @Override
    public List<String> getChecklist() {
        List<String> result = super.getChecklist();
        result.add("Does it have a roof?");
        return result;
    }

    @Override
    public int getMonthlyPayment() {
        return (int) (getAskingPrice() / 12.0);
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
