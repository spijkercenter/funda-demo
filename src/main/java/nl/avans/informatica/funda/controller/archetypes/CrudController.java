package nl.avans.informatica.funda.controller.archetypes;

public interface CrudController<DTO> extends
        CanCreate<DTO>,
        CanRead<DTO>,
        CanUpdate<DTO>,
        CanDelete {
}
