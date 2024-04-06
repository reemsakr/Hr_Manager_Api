package gov.iti.jets.web.mapper;

import gov.iti.jets.web.dto.PositionDto;
import gov.iti.jets.web.persistence.entities.Position;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T17:55:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position toEntity(PositionDto positionDto) {
        if ( positionDto == null ) {
            return null;
        }

        Position position = new Position();

        position.setId( positionDto.getId() );
        position.setTitle( positionDto.getTitle() );
        position.setDescription( positionDto.getDescription() );

        return position;
    }

    @Override
    public PositionDto toDto(Position position) {
        if ( position == null ) {
            return null;
        }

        Integer id = null;
        String title = null;
        String description = null;

        id = position.getId();
        title = position.getTitle();
        description = position.getDescription();

        PositionDto positionDto = new PositionDto( id, title, description );

        return positionDto;
    }

    @Override
    public Position partialUpdate(PositionDto positionDto, Position position) {
        if ( positionDto == null ) {
            return position;
        }

        if ( positionDto.getId() != null ) {
            position.setId( positionDto.getId() );
        }
        if ( positionDto.getTitle() != null ) {
            position.setTitle( positionDto.getTitle() );
        }
        if ( positionDto.getDescription() != null ) {
            position.setDescription( positionDto.getDescription() );
        }

        return position;
    }
}
