package nl.avans.informatica.funda.service;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Customer;
import nl.avans.informatica.funda.domain.Property;
import nl.avans.informatica.funda.repository.BidRepository;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class BidServiceTest {

    private BidRepository bidRepository;
    private PropertyRepository propertyRepository;

    private BidService sut;

    @BeforeEach
    void beforeEach() {
        bidRepository = mock(BidRepository.class);
        propertyRepository = mock(PropertyRepository.class);
        sut = new BidService(bidRepository, propertyRepository);

        when(bidRepository.save(any(Bid.class))).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void isAcceptable_forLowBall_expectFalse() {
        // Arrange
        var customer = mock(Customer.class);
        var property = mock(Property.class);
        when(property.getAskingPrice()).thenReturn(100);
        var given = new Bid(99, customer, property);

        // Act
        var actual = sut.isAcceptable(given);

        // Assert
        assertFalse(actual);
    }

    @Test
    void isAcceptable_forEqual_expectTrue() {
        // Arrange
        var customer = mock(Customer.class);
        var property = mock(Property.class);
        when(property.getAskingPrice()).thenReturn(100);
        var given = new Bid(101, customer, property);

        // Act
        var actual = sut.isAcceptable(given);

        // Assert
        assertTrue(actual);
    }

    @Test
    void isAcceptable_forHighBall_expectTrue() {
        // Arrange
        var customer = mock(Customer.class);
        var property = mock(Property.class);
        when(property.getAskingPrice()).thenReturn(100);
        var given = new Bid(101, customer, property);

        // Act
        var actual = sut.isAcceptable(given);

        // Assert
        assertTrue(actual);
    }

    @Test
    void save_forAcceptableOffer_expectBidAddedAndSaved() {
        // Arrange
        var property = mock(Property.class);
        when(property.getAskingPrice()).thenReturn(100);

        var given = mock(Bid.class);
        when(given.getPriceOffered()).thenReturn(200);
        when(given.getProperty()).thenReturn(property);

        // Act
        sut.save(given);

        // Assert
        verify(bidRepository, times(1)).save(given);
        verify(propertyRepository, times(1)).save(property);
        verify(property, times(1)).addBid(given);
    }

    @Test
    void save_forUnacceptableOffer_expectIllegalArgumentExceptionAndNothingSaved() {
        // Arrange
        var property = mock(Property.class);
        when(property.getAskingPrice()).thenReturn(100);

        var given = mock(Bid.class);
        when(given.getPriceOffered()).thenReturn(50);
        when(given.getProperty()).thenReturn(property);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                sut.save(given)
        );

        verifyNoInteractions(bidRepository);
        verifyNoInteractions(propertyRepository);
        verify(property, never()).addBid(given);
    }
}
