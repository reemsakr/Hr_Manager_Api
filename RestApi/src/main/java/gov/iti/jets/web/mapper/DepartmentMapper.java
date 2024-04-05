package gov.iti.jets.web.mapper;

import gov.iti.jets.web.dto.DepartmentDto;
import gov.iti.jets.web.persistence.entities.Department;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    Department toEntity(DepartmentDto departmentDto);

    DepartmentDto toDto(Department department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentDto departmentDto, @MappingTarget Department department);
}