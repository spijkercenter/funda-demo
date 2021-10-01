package nl.avans.informatica.funda.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

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
