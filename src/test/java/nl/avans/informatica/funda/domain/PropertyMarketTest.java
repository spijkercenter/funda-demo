package nl.avans.informatica.funda.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyMarketTest {

    @Test
    void search_forOneBelowAndOneAboveAsking_expectOne() {
        // Arrange
        PropertyMarket market = new PropertyMarket();
        Property below = new Property("", 5);
        Property above = new Property("", 10);
        market.addProperty(below);
        market.addProperty(above);

        List<Property> expected = new ArrayList<>();
        expected.add(below);

        // Act
        List<Property> actual = market.search(8);

        // Assert
        assertNotNull(actual);
    }

    @Test
    void search_forOneBelowAndOneGoldilocksAndOneAboveAsking_expectOne() {
        // Arrange
        PropertyMarket market = new PropertyMarket();
        Property below = new Property("",5);
        Property goldilocks = new Property("", 7);
        Property above = new Property("", 10);
        market.addProperty(below);
        market.addProperty(goldilocks);
        market.addProperty(above);

        List<Property> expected = new ArrayList<>();
        expected.add(goldilocks);

        // Act
        List<Property> actual = market.search(6, 8);

        // Assert
        assertEquals(expected, actual);
    }

}
