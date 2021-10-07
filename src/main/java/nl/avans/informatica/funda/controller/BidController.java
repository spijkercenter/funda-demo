package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CanCreate;
import nl.avans.informatica.funda.controller.archetypes.CanRead;
import nl.avans.informatica.funda.controller.dto.BidDto;
import nl.avans.informatica.funda.controller.mapper.BidMapper;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.service.BidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController implements CanRead<BidDto>, CanCreate<BidDto> {

    private final BidService bidService;
    private final ControllerOperations<Bid, BidDto> operations;

    public BidController(BidService bidService, BidMapper mapper) {
        this.bidService = bidService;
        this.operations = new ControllerOperations<>(mapper);
    }

    @Override
    @GetMapping
    public List<BidDto> getAll() {
        return operations.getAll(bidService::findAll);

    }

    @Override
    @GetMapping("{id}")
    public BidDto getById(@PathVariable Integer id) {
        return operations.getById(id, bidService::findById);
    }

    @Override
    @PostMapping
    public BidDto create(@RequestBody BidDto dto) {
        return operations.create(dto, bidService::save);
    }
}
