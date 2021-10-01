package nl.avans.informatica.funda.controller.dto;

import nl.avans.informatica.funda.controller.HasId;

public class CustomerDto implements HasId {
    private final Integer id;
    private final String email;
    private final String name;

    public CustomerDto(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
