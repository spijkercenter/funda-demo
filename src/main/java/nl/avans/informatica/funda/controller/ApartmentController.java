package nl.avans.informatica.funda.controller;

import nl.avans.informatica.funda.controller.archetypes.CanCreate;
import nl.avans.informatica.funda.controller.archetypes.CanRead;
import nl.avans.informatica.funda.controller.dto.ApartmentDto;
import nl.avans.informatica.funda.domain.Bid;
import nl.avans.informatica.funda.domain.Apartment;
import nl.avans.informatica.funda.repository.ApartmentRepository;
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
@RequestMapping("/apartments")
public class ApartmentController implements CanRead<Apartment, ApartmentDto>, CanCreate<Apartment, ApartmentDto> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AbstractController<Apartment, ApartmentDto> innerController;

    public ApartmentController(ApartmentRepository apartmentRepository) {
        this.innerController = new AbstractController<>(logger, apartmentRepository) {
            @Override
            protected ApartmentDto fromEntityToDto(Apartment apartment) {
                return new ApartmentDto(
                        apartment.getId(),
                        apartment.getType(),
                        apartment.getAddress(),
                        apartment.getAskingPrice(),
                        apartment.getMonthlyPayment(),
                        apartment.getBids().stream().mapToInt(Bid::getId).boxed().collect(Collectors.toList()),
                        apartment.getChecklist(),
                        apartment.getPaymentHoa(),
                        apartment.getFloor()
                );
            }

            @Override
            protected Apartment fromDtoToEntity(ApartmentDto dto) {
                return new Apartment(
                        dto.getAddress(),
                        dto.getAskingPrice(),
                        dto.getPaymentHoa(),
                        dto.getFloor()
                );
            }
        };
    }

    @Override
    @PostMapping
    public ResponseEntity<ApartmentDto> create(@RequestBody ApartmentDto apartmentDto) {
        return innerController.create(apartmentDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ApartmentDto>> getAll() {
        return innerController.getAll();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ApartmentDto> getById(@PathVariable Integer id) {
        return innerController.getById(id);
    }
}
