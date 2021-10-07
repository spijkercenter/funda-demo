package nl.avans.informatica.funda.controller.archetypes;

import java.util.List;

public interface CanRead<DTO> {
    List<DTO> getAll();

    DTO getById(Integer id);
}
