package nl.avans.informatica.funda.repositories;

import nl.avans.informatica.funda.domain.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Integer> {

    @Query("select p from Property p where p.askingPrice between :minPrice and :maxPrice")
    Iterable<Property> findAllByAskingPriceBetween(int minPrice, int maxPrice);

}
