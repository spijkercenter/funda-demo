package nl.avans.informatica.funda.repository;

import nl.avans.informatica.funda.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface BidRepository extends CrudRepository<Bid, Integer> {
}
