package nl.avans.informatica.funda.repositories;

import nl.avans.informatica.funda.domain.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Integer> {
    Iterable<Bid> findAllByProperty_Id(int propertyId);
}
