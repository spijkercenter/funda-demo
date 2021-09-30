package nl.avans.informatica.funda.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    @Test
    void doOffer_forLowBall_expectNull() {
        Property property = new House("", 10, 19);
        Customer customer = new Customer("", "");
        int offerPrice = 8;

        Bid actual = property.doOffer(customer, offerPrice);

        assertNull(actual);
    }

    @Test
    void doOffer_forHighOffer_expectBid() {
        Property property = new House("", 10, 0);
        Customer customer = new Customer("", "");
        int offerPrice = 12;

        Bid actual = property.doOffer(customer, offerPrice);

        assertNotNull( actual);
    }

    @Test
    void checklist_expect2() {
        Property property = new House("", 10, 0);

        List<String> actual = property.getChecklist();
        System.out.println(actual);

        assertEquals(2, actual.size());
    }

    @Test
    void printChecklist() {

        Property property = new House("", 10, 0);

        property.printChecklist();

    }

}
