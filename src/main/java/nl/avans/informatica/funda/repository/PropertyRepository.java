package nl.avans.informatica.funda.repository;

import nl.avans.informatica.funda.domain.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer> {

    Optional<Property> findByAddress(String address);

    Iterable<Property> findByAskingPriceLessThan(int maxPrice);

    Iterable<Property> findByAskingPriceBetween(int minPrice, int maxPrice);
}
