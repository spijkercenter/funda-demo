package nl.avans.informatica.funda.controller.mapper;


public interface Mapper<E, DTO> {
    DTO fromEntityToDto(E entity);

    E fromDtoToEntity(DTO dto);
}
