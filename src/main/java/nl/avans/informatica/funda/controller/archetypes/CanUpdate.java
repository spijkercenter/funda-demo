package nl.avans.informatica.funda.controller.archetypes;

import nl.avans.informatica.funda.controller.HasId;
import nl.avans.informatica.funda.domain.HasModifiableId;
import org.springframework.http.ResponseEntity;

public interface CanUpdate<E extends HasModifiableId, DTO extends HasId> {
    ResponseEntity<DTO> update(Integer id, DTO dto);
}
