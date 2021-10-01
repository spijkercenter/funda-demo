package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CanCreate;
import nl.avans.informatica.funda.controller.archetypes.CanRead;
import nl.avans.informatica.funda.controller.dto.GarageDto;
import nl.avans.informatica.funda.domain.Garage;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.repository.GarageRepository;
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
@RequestMapping("/garages")
public class GarageController implements CanRead<Garage, GarageDto>, CanCreate<Garage, GarageDto> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AbstractController<Garage, GarageDto> innerController;

    public GarageController(GarageRepository garageRepository) {
        this.innerController = new AbstractController<>(logger, garageRepository) {
            @Override
            protected GarageDto fromEntityToDto(Garage garage) {
                return new GarageDto(
                        garage.getId(),
                        garage.getType(),
                        garage.getAddress(),
                        garage.getAskingPrice(),
                        garage.getMonthlyPayment(),
                        garage.getBids().stream().mapToInt(Bid::getId).boxed().collect(Collectors.toList()),
                        garage.getChecklist(),
                        garage.hasElectricity()
                );
            }

            @Override
            protected Garage fromDtoToEntity(GarageDto dto) {
                return new Garage(
                        dto.getAddress(),
                        dto.getAskingPrice(),
                        dto.getHasElectricity()
                );
            }
        };
    }

    @Override
    @PostMapping
    public ResponseEntity<GarageDto> create(@RequestBody GarageDto garageDto) {
        return innerController.create(garageDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<GarageDto>> getAll() {
        return innerController.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<GarageDto> getById(@PathVariable Integer id) {
        return innerController.getById(id);
    }
}
