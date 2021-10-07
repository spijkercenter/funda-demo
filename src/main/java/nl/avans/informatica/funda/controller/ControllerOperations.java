package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ControllerOperations<E, DTO> {

    private final Mapper<E, DTO> mapper;

    public ControllerOperations(Mapper<E, DTO> mapper) {
        this.mapper = mapper;
    }

    public DTO create(DTO dto, UnaryOperator<E> saveMethod) {
        try {
            E entity = mapper.fromDtoToEntity(dto);
            saveMethod.apply(entity);
            return mapper.fromEntityToDto(entity);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void deleteById(Integer id, Predicate<Integer> existsByIdMethod, Consumer<Integer> deleteByIdMethod) {
        if (!existsByIdMethod.test(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        deleteByIdMethod.accept(id);
    }

    public List<DTO> getAll(Supplier<List<E>> getAllMethod) {
        return getAllMethod.get().stream()
                .map(mapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public DTO getById(Integer id, Function<Integer, Optional<E>> getByIdMethod) {
        return getByIdMethod.apply(id)
                .map(mapper::fromEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public DTO update(Integer id,
                      DTO dto,
                      Predicate<Integer> existsByIdMethod,
                      UnaryOperator<E> saveMethod,
                      BiConsumer<E, Integer> entityIdSetter
    ) {
        if (id == null || dto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!existsByIdMethod.test(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        E entity = mapper.fromDtoToEntity(dto);
        entityIdSetter.accept(entity, id);
        entity = saveMethod.apply(entity);
        return mapper.fromEntityToDto(entity);
    }
}
