package nl.avans.informatica.funda.controller.archetypes;

import nl.avans.informatica.funda.controller.HasId;
import nl.avans.informatica.funda.domain.HasModifiableId;
import org.springframework.http.ResponseEntity;

public interface CanDelete<E extends HasModifiableId, DTO extends HasId> {
    ResponseEntity<Void> deleteById(Integer id);
}
