package nl.avans.informatica.funda;

import nl.avans.informatica.funda.domain.HasModifiableId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// This is an interface that enables us to use both Services or JpaRepositories as sources for our AbstractController
public interface DataSource<E extends HasModifiableId> {

    static <E extends HasModifiableId> DataSource<E> of(JpaRepository<E, Integer> repository) {
        return new DataSource<>() {
            @Override
            public List<E> findAll() {
                return repository.findAll();
            }

            @Override
            public Optional<E> findById(Integer id) {
                return repository.findById(id);
            }

            @Override
            public E save(E entity) {
                return repository.save(entity);
            }

            @Override
            public boolean existsById(Integer id) {
                return repository.existsById(id);
            }

            @Override
            public void deleteById(Integer id) {
                repository.deleteById(id);
            }
        };
    }

    List<E> findAll();

    Optional<E> findById(Integer id);

    E save(E entity);

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean existsById(Integer id);

    void deleteById(Integer id);
}
