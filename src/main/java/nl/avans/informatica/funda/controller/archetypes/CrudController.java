package nl.avans.informatica.funda.controller.archetypes;

import nl.avans.informatica.funda.controller.HasId;
import nl.avans.informatica.funda.domain.HasModifiableId;

public interface CrudController<E extends HasModifiableId, DTO extends HasId> extends
        CanCreate<E, DTO>,
        CanRead<E, DTO>,
        CanUpdate<E, DTO>,
        CanDelete<E, DTO> {
}
