package gov.iti.jets.web.mapper;

import gov.iti.jets.web.model.dto.AttendanceDto;
import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.dto.PositionDto;
import gov.iti.jets.web.persistence.entities.Attendance;
import gov.iti.jets.web.persistence.entities.Department;
import gov.iti.jets.web.persistence.entities.Employee;
import gov.iti.jets.web.persistence.entities.Position;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-07T23:59:23+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public Attendance toEntity(AttendanceDto attendanceDto) {
        if ( attendanceDto == null ) {
            return null;
        }

        Attendance attendance = new Attendance();

        attendance.setId( attendanceDto.getId() );
        attendance.setEmployee( employeeDtoToEmployee( attendanceDto.getEmployee() ) );
        attendance.setAttendanceDate( attendanceDto.getAttendanceDate() );
        attendance.setTimeIn( attendanceDto.getTimeIn() );
        attendance.setTimeOut( attendanceDto.getTimeOut() );

        return attendance;
    }

    @Override
    public AttendanceDto toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        Integer id = null;
        Date attendanceDate = null;
        LocalTime timeIn = null;
        LocalTime timeOut = null;
        EmployeeDto employee = null;

        id = attendance.getId();
        attendanceDate = attendance.getAttendanceDate();
        timeIn = attendance.getTimeIn();
        timeOut = attendance.getTimeOut();
        employee = employeeToEmployeeDto( attendance.getEmployee() );

        AttendanceDto attendanceDto = new AttendanceDto( id, attendanceDate, timeIn, timeOut, employee );

        return attendanceDto;
    }

    @Override
    public Attendance partialUpdate(AttendanceDto attendanceDto, Attendance attendance) {
        if ( attendanceDto == null ) {
            return attendance;
        }

        if ( attendanceDto.getId() != null ) {
            attendance.setId( attendanceDto.getId() );
        }
        if ( attendanceDto.getEmployee() != null ) {
            if ( attendance.getEmployee() == null ) {
                attendance.setEmployee( new Employee() );
            }
            employeeDtoToEmployee1( attendanceDto.getEmployee(), attendance.getEmployee() );
        }
        if ( attendanceDto.getAttendanceDate() != null ) {
            attendance.setAttendanceDate( attendanceDto.getAttendanceDate() );
        }
        if ( attendanceDto.getTimeIn() != null ) {
            attendance.setTimeIn( attendanceDto.getTimeIn() );
        }
        if ( attendanceDto.getTimeOut() != null ) {
            attendance.setTimeOut( attendanceDto.getTimeOut() );
        }

        return attendance;
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

    protected EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        Integer id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        LocalDate dateOfBirth = null;
        String gender = null;
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
