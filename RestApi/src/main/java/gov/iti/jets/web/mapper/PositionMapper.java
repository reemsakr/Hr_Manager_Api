package gov.iti.jets.web.mapper;

import gov.iti.jets.web.dto.PositionDto;
import gov.iti.jets.web.persistence.entities.Position;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PositionMapper {
    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);
    Position toEntity(PositionDto positionDto);

    PositionDto toDto(Position position);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Position partialUpdate(PositionDto positionDto, @MappingTarget Position position);
}