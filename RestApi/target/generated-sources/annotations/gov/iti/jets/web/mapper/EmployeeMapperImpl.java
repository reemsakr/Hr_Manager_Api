package gov.iti.jets.web.mapper;

import gov.iti.jets.web.dto.DepartmentDto;
import gov.iti.jets.web.dto.EmployeeDto;
import gov.iti.jets.web.dto.PositionDto;
import gov.iti.jets.web.persistence.entities.Department;
import gov.iti.jets.web.persistence.entities.Employee;
import gov.iti.jets.web.persistence.entities.Position;
import gov.iti.jets.web.persistence.enums.Gender;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T01:45:09+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setEmail( employeeDto.getEmail() );
        employee.setDateOfBirth( employeeDto.getDateOfBirth() );
        employee.setGender( employeeDto.getGender() );
        employee.setHireDate( employeeDto.getHireDate() );
        employee.setDepartment( departmentDtoToDepartment( employeeDto.getDepartment() ) );
        employee.setPosition( positionDtoToPosition( employeeDto.getPosition() ) );
        employee.setSalary( employeeDto.getSalary() );

        return employee;
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        Integer id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        LocalDate dateOfBirth = null;
        Gender gender = null;
        LocalDate hireDate = null;
        DepartmentDto department = null;
        PositionDto position = null;
        BigDecimal salary = null;

        id = employee.getId();
        firstName = employee.getFirstName();
        lastName = employee.getLastName();
        email = employee.getEmail();
        dateOfBirth = employee.getDateOfBirth();
        gender = employee.getGender();
        hireDate = employee.getHireDate();
        department = departmentToDepartmentDto( employee.getDepartment() );
        position = positionToPositionDto( employee.getPosition() );
        salary = employee.getSalary();

        EmployeeDto employeeDto = new EmployeeDto( id, firstName, lastName, email, dateOfBirth, gender, hireDate, department, position, salary );

        return employeeDto;
    }

    @Override
    public Employee partialUpdate(EmployeeDto employeeDto, Employee employee) {
        if ( employeeDto == null ) {
            return employee;
        }

        if ( employeeDto.getId() != null ) {
            employee.setId( employeeDto.getId() );
        }
        if ( employeeDto.getFirstName() != null ) {
            employee.setFirstName( employeeDto.getFirstName() );
        }
        if ( employeeDto.getLastName() != null ) {
            employee.setLastName( employeeDto.getLastName() );
        }
        if ( employeeDto.getEmail() != null ) {
            employee.setEmail( employeeDto.getEmail() );
        }
        if ( employeeDto.getDateOfBirth() != null ) {
            employee.setDateOfBirth( employeeDto.getDateOfBirth() );
        }
        if ( employeeDto.getGender() != null ) {
            employee.setGender( employeeDto.getGender() );
        }
        if ( employeeDto.getHireDate() != null ) {
            employee.setHireDate( employeeDto.getHireDate() );
        }
        if ( employeeDto.getDepartment() != null ) {
            if ( employee.getDepartment() == null ) {
                employee.setDepartment( new Department() );
            }
            departmentDtoToDepartment1( employeeDto.getDepartment(), employee.getDepartment() );
        }
        if ( employeeDto.getPosition() != null ) {
            if ( employee.getPosition() == null ) {
                employee.setPosition( new Position() );
            }
            positionDtoToPosition1( employeeDto.getPosition(), employee.getPosition() );
        }
        if ( employeeDto.getSalary() != null ) {
            employee.setSalary( employeeDto.getSalary() );
        }

        return employee;
    }

    protected Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if ( departmentDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDto.getId() );
        department.setName( departmentDto.getName() );

        return department;
    }

    protected Position positionDtoToPosition(PositionDto positionDto) {
        if ( positionDto == null ) {
            return null;
        }

        Position position = new Position();

        position.setId( positionDto.getId() );
        position.setTitle( positionDto.getTitle() );
        position.setDescription( positionDto.getDescription() );

        return position;
    }

    protected DepartmentDto departmentToDepartmentDto(Department department) {
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

    protected PositionDto positionToPositionDto(Position position) {
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

    protected void departmentDtoToDepartment1(DepartmentDto departmentDto, Department mappingTarget) {
        if ( departmentDto == null ) {
            return;
        }

        if ( departmentDto.getId() != null ) {
            mappingTarget.setId( departmentDto.getId() );
        }
        if ( departmentDto.getName() != null ) {
            mappingTarget.setName( departmentDto.getName() );
        }
    }

    protected void positionDtoToPosition1(PositionDto positionDto, Position mappingTarget) {
        if ( positionDto == null ) {
            return;
        }

        if ( positionDto.getId() != null ) {
            mappingTarget.setId( positionDto.getId() );
        }
        if ( positionDto.getTitle() != null ) {
            mappingTarget.setTitle( positionDto.getTitle() );
        }
        if ( positionDto.getDescription() != null ) {
            mappingTarget.setDescription( positionDto.getDescription() );
        }
    }
}
