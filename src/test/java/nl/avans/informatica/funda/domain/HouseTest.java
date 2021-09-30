package nl.avans.informatica.funda.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    void getCheckList_expect3() {
        House property = new House("", 10, 10);

        List<String> actual = property.getChecklist();
        System.out.println(actual);

        assertEquals(3, actual.size());
    }

    @Test
    void printChecklist() {

        Property property = new House("", 10, 10);

        property.printChecklist();

    }




}
