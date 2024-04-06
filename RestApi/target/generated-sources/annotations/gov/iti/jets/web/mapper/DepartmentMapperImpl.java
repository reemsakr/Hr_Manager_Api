package gov.iti.jets.web.mapper;

import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.persistence.entities.Department;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-07T00:40:58+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toEntity(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDto.getId() );
        department.setName( departmentDto.getName() );

        return department;
    }

    @Override
    public DepartmentDto toDto(Department department) {
        if ( department == null ) {
            return null;
        }

        Integer id = null;
        String name = null;

        id = department.getId();
        name = department.getName();

        DepartmentDto departmentDto = new DepartmentDto( id, name );

        return departmentDto;
    }

    @Override
    public Department partialUpdate(DepartmentDto departmentDto, Department department) {
        if ( departmentDto == null ) {
            return department;
        }

        if ( departmentDto.getId() != null ) {
            department.setId( departmentDto.getId() );
        }
        if ( departmentDto.getName() != null ) {
            department.setName( departmentDto.getName() );
        }

        return department;
    }
}
