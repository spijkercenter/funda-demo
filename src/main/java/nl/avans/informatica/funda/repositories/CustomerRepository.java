package nl.avans.informatica.funda.repositories;

import nl.avans.informatica.funda.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Integer> {

}
