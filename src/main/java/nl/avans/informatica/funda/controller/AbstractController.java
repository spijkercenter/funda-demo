package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.DataSource;
import nl.avans.informatica.funda.controller.archetypes.CrudController;
import nl.avans.informatica.funda.domain.HasModifiableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

public abstract class AbstractController<E extends HasModifiableId, DTO extends HasId> implements CrudController<E, DTO> {

    private final DataSource<E> dataSource;

    protected AbstractController(DataSource<E> dataSource) {
        this.dataSource = dataSource;
    }

    protected AbstractController(JpaRepository<E, Integer> repository) {
        this(DataSource.of(repository));
    }

    protected abstract DTO fromEntityToDto(E t);

    protected abstract E fromDtoToEntity(DTO dto);

    @Override
    public ResponseEntity<List<DTO>> getAll() {
        return ok(dataSource.findAll().stream()
                .map(this::fromEntityToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<DTO> getById(Integer id) {
        return dataSource.findById(id)
                .map(this::fromEntityToDto)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @Override
    public ResponseEntity<DTO> create(DTO input) {
        E entity;
        try {
            entity = fromDtoToEntity(input);
            dataSource.save(entity);
            return ok(fromEntityToDto(entity));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<DTO> update(Integer id, DTO dto) {
        if (dto == null || id == null) {
            return badRequest().build();
        }
        // If there is an ID in the body, check if it is the same as in the path
        if (dto.getId() != null && dto.getId().intValue() != id) {
            return badRequest().build();
        }

        if (!dataSource.existsById(id)) {
            return notFound().build();
        }

        E entity = fromDtoToEntity(dto);
        entity.setId(id);
        entity = dataSource.save(entity);
        return ok(fromEntityToDto(entity));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        if (!dataSource.existsById(id)) {
            return notFound().build();
        }
        dataSource.deleteById(id);
        return noContent().build();
    }
}
