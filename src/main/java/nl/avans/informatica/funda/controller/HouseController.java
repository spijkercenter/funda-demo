package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CanCreate;
import nl.avans.informatica.funda.controller.archetypes.CanRead;
import nl.avans.informatica.funda.controller.dto.HouseDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.House;
import nl.avans.informatica.funda.repository.HouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/houses")
public class HouseController implements CanRead<House, HouseDto>, CanCreate<House, HouseDto> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AbstractController<House, HouseDto> innerController;

    public HouseController(HouseRepository houseRepository) {
        this.innerController = new AbstractController<>(logger, houseRepository) {
            @Override
            protected HouseDto fromEntityToDto(House house) {
                return new HouseDto(
                        house.getId(),
                        house.getType(),
                        house.getAddress(),
                        house.getAskingPrice(),
                        house.getMonthlyPayment(),
                        house.getBids().stream().mapToInt(Bid::getId).boxed().collect(Collectors.toList()),
                        house.getChecklist(),
                        house.getPlotArea()
                );
            }

            @Override
            protected House fromDtoToEntity(HouseDto dto) {
                return new House(
                        dto.getAddress(),
                        dto.getAskingPrice(),
                        dto.getPlotArea()
                );
            }
        };
    }

    @Override
    @PostMapping
    public ResponseEntity<HouseDto> create(@RequestBody HouseDto houseDto) {
        return innerController.create(houseDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<HouseDto>> getAll() {
        return innerController.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<HouseDto> getById(@PathVariable Integer id) {
        return innerController.getById(id);
    }
}
