package nl.avans.informatica.funda.repository;

import nl.avans.informatica.funda.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Optional<Property> findByAddress(String address);

    Iterable<Property> findByAskingPriceLessThan(int maxPrice);

    @Query("select p from Property p where p.askingPrice >= :minPrice and p.askingPrice <= :maxPrice")
    List<Property> findByAskingPriceBetween(int minPrice, int maxPrice);
}
