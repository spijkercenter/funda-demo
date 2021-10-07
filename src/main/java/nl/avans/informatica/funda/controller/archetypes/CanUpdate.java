package nl.avans.informatica.funda.controller.archetypes;

public interface CanUpdate<DTO> {
    DTO update(Integer id, DTO dto);
}
