package nl.avans.informatica.funda.repositories;

import nl.avans.informatica.funda.domain.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// NOTE: this is only an interface, not an implementation. Spring Data JPA handles all this!
public interface PropertyRepository extends CrudRepository<Property, Integer> {

    Iterable<Property> findAllByAskingPriceBetween(int minPrice, int maxPrice);

    Iterable<Property> findAllByAddressEquals(String address);

}
