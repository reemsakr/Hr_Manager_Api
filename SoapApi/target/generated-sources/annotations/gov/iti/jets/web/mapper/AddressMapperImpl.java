package gov.iti.jets.web.mapper;

import gov.iti.jets.web.model.dto.AddressDto;
import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.dto.PositionDto;
import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.entities.Department;
import gov.iti.jets.web.persistence.entities.Employee;
import gov.iti.jets.web.persistence.entities.Position;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T16:23:17+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDto.getId() );
        address.setEmployee( employeeDtoToEmployee( addressDto.getEmployee() ) );
        address.setStreet( addressDto.getStreet() );
        address.setCity( addressDto.getCity() );
        address.setPostalCode( addressDto.getPostalCode() );

        return address;
    }

    @Override
    public AddressDto toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setId( address.getId() );
        addressDto.setStreet( address.getStreet() );
        addressDto.setCity( address.getCity() );
        addressDto.setPostalCode( address.getPostalCode() );
        addressDto.setEmployee( employeeToEmployeeDto( address.getEmployee() ) );

        return addressDto;
    }

    @Override
    public Address partialUpdate(AddressDto addressDto, Address address) {
        if ( addressDto == null ) {
            return address;
        }

        if ( addressDto.getId() != null ) {
            address.setId( addressDto.getId() );
        }
        if ( addressDto.getEmployee() != null ) {
            if ( address.getEmployee() == null ) {
                address.setEmployee( new Employee() );
            }
            employeeDtoToEmployee1( addressDto.getEmployee(), address.getEmployee() );
        }
        if ( addressDto.getStreet() != null ) {
            address.setStreet( addressDto.getStreet() );
        }
        if ( addressDto.getCity() != null ) {
            address.setCity( addressDto.getCity() );
        }
        if ( addressDto.getPostalCode() != null ) {
            address.setPostalCode( addressDto.getPostalCode() );
        }

        return address;
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

    protected Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
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

    protected DepartmentDto departmentToDepartmentDto(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setId( department.getId() );
        departmentDto.setName( department.getName() );

        return departmentDto;
    }

    protected PositionDto positionToPositionDto(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionDto positionDto = new PositionDto();

        positionDto.setId( position.getId() );
        positionDto.setTitle( position.getTitle() );
        positionDto.setDescription( position.getDescription() );

        return positionDto;
    }

    protected EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setFirstName( employee.getFirstName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setEmail( employee.getEmail() );
        employeeDto.setDateOfBirth( employee.getDateOfBirth() );
        employeeDto.setGender( employee.getGender() );
        employeeDto.setHireDate( employee.getHireDate() );
        employeeDto.setDepartment( departmentToDepartmentDto( employee.getDepartment() ) );
        employeeDto.setPosition( positionToPositionDto( employee.getPosition() ) );
        employeeDto.setSalary( employee.getSalary() );

        return employeeDto;
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

    protected void employeeDtoToEmployee1(EmployeeDto employeeDto, Employee mappingTarget) {
        if ( employeeDto == null ) {
            return;
        }

        if ( employeeDto.getId() != null ) {
            mappingTarget.setId( employeeDto.getId() );
        }
        if ( employeeDto.getFirstName() != null ) {
            mappingTarget.setFirstName( employeeDto.getFirstName() );
        }
        if ( employeeDto.getLastName() != null ) {
            mappingTarget.setLastName( employeeDto.getLastName() );
        }
        if ( employeeDto.getEmail() != null ) {
            mappingTarget.setEmail( employeeDto.getEmail() );
        }
        if ( employeeDto.getDateOfBirth() != null ) {
            mappingTarget.setDateOfBirth( employeeDto.getDateOfBirth() );
        }
        if ( employeeDto.getGender() != null ) {
            mappingTarget.setGender( employeeDto.getGender() );
        }
        if ( employeeDto.getHireDate() != null ) {
            mappingTarget.setHireDate( employeeDto.getHireDate() );
        }
        if ( employeeDto.getDepartment() != null ) {
            if ( mappingTarget.getDepartment() == null ) {
                mappingTarget.setDepartment( new Department() );
            }
            departmentDtoToDepartment1( employeeDto.getDepartment(), mappingTarget.getDepartment() );
        }
        if ( employeeDto.getPosition() != null ) {
            if ( mappingTarget.getPosition() == null ) {
                mappingTarget.setPosition( new Position() );
            }
            positionDtoToPosition1( employeeDto.getPosition(), mappingTarget.getPosition() );
        }
        if ( employeeDto.getSalary() != null ) {
            mappingTarget.setSalary( employeeDto.getSalary() );
        }
    }
}
