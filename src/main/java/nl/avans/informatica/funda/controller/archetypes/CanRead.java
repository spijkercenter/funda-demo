package nl.avans.informatica.funda.controller.archetypes;

import nl.avans.informatica.funda.controller.HasId;
import nl.avans.informatica.funda.domain.HasModifiableId;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CanRead<E extends HasModifiableId, DTO extends HasId> {
    ResponseEntity<List<DTO>> getAll();
    ResponseEntity<DTO> getById(Integer id);
}
