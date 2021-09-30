package nl.avans.informatica.funda.domain;

public class House extends Property {
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
}
