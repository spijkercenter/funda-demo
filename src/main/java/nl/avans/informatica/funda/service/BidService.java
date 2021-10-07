package nl.avans.informatica.funda.service;

import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.repository.BidRepository;
import nl.avans.informatica.funda.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    private final BidRepository bidRepository;
    private final PropertyRepository propertyRepository;

    public BidService(BidRepository bidRepository, PropertyRepository propertyRepository) {
        this.bidRepository = bidRepository;
        this.propertyRepository = propertyRepository;
    }

    public boolean isAcceptable(Bid bid) {
        return bid.getPriceOffered() >= bid.getProperty().getAskingPrice();
    }

    @Transactional // Rolls back the first save operation if the second one fails
    public Bid save(Bid bid) {
        if (!isAcceptable(bid)) {
            throw new IllegalArgumentException();
        }
        bid = bidRepository.save(bid);
        var property = bid.getProperty();

        property.addBid(bid);
        propertyRepository.save(bid.getProperty());

        return bid;
    }

    // region repo pass through
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    public Optional<Bid> findById(Integer id) {
        return bidRepository.findById(id);
    }


    public boolean existsById(Integer id) {
        return bidRepository.existsById(id);
    }

    public void deleteById(Integer id) {
        bidRepository.deleteById(id);
    }
    // endregion
}
