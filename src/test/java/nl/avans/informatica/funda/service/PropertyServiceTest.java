package nl.avans.informatica.funda.service;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.House;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PropertyServiceTest {

    private PropertyService sut;

    @BeforeEach
    public void beforeEach() {
        PropertyRepository repository = mock(PropertyRepository.class);

        Property property = mock(Property.class);
        when(property.getAskingPrice()).thenReturn(50);

        when(repository.getById(1)).thenReturn(property);

        sut = new PropertyService(repository);
    }

    @Test
    void doOffer_forLowBall_expectNull() {

        Property property = new House("", 10, 19);
        Customer customer = new Customer("", "");
        int offerPrice = 8;

        Bid actual = sut.doOffer(property, customer, offerPrice);

        assertNull(actual);
    }

    @Test
    void doOffer_forHighOffer_expectBid() {
        Property property = new House("", 10, 0);
        Customer customer = new Customer("", "");
        int offerPrice = 12;

        Bid actual = sut.doOffer(property, customer, offerPrice);

        assertNotNull(actual);
    }

    @Test
    void isAccepted_forExistingIdAndHighball_expectTrue() {
        boolean actual = sut.isAccepted(1, 100);

        assertTrue(actual);
    }

    @Test
    void isAccepted_forExistingIdAndLowball_expectFalse() {
        boolean actual = sut.isAccepted(1, 10);

        assertFalse(actual);
    }


    @Test
    void printChecklist() {
        Property property = new House("", 10, 0);

        sut.printChecklist(property);

    }

}
