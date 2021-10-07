package nl.avans.informatica.funda.controller.archetypes;

public interface CanCreate<DTO> {
    DTO create(DTO dto);
}
