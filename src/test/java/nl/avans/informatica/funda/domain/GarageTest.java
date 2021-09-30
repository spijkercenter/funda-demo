package nl.avans.informatica.funda.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {

    @Test
    void getCheckList_expect0() {
        Garage property = new Garage("", 10, false);

        List<String> actual = property.getChecklist();
        System.out.println(actual);

        assertEquals(0, actual.size());
    }

}
