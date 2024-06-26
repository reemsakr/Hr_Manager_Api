package gov.iti.jets.web.mapper;

import gov.iti.jets.web.model.dto.AttendanceDto;
import gov.iti.jets.web.persistence.entities.Attendance;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);
    Attendance toEntity(AttendanceDto attendanceDto);

    AttendanceDto toDto(Attendance attendance);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Attendance partialUpdate(AttendanceDto attendanceDto, @MappingTarget Attendance attendance);
}