package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.*;
import gov.iti.jets.web.model.requestBody.EmployeeBodyReq;
import gov.iti.jets.web.service.DepartmentServices;
import gov.iti.jets.web.service.EmployeeServices;
import gov.iti.jets.web.service.PositionServices;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebService
public class EmployeeResource {

    private EmployeeServices employeeServices;
    public EmployeeResource(){
        employeeServices = new EmployeeServices();
    }

    @WebResult(name="employees")
    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeDto> employeeDtoList = employeeServices.getAllEmployees();
        return employeeDtoList;
    }


    @WebResult(name="employee")
    public EmployeeDto getEmployeeById(@WebParam(name="id") int id){
        Optional<EmployeeDto> employeeDto = employeeServices.getEmployeeById(id);
        if(employeeDto.isPresent()){

            return employeeDto.get();
        }
        else{
            throw new RuntimeException("Wrong credentials");
        }
    }

    @WebResult(name="addEmployee")
    public String addEmployee(EmployeeBodyReq employeeBodyReq){
        Optional<DepartmentDto> departmentDto = new DepartmentServices().getDepartmentById(employeeBodyReq.getDepartmentId());
        Optional<PositionDto> positionDto = new PositionServices().getPositionById(employeeBodyReq.getPositionId());
        if(!departmentDto.isPresent()){
            return "Department not found";
        }
        if(!positionDto.isPresent()){
            return "Position not found";
        }
        EmployeeDto employeeDto = new EmployeeDto(
                null,
                employeeBodyReq.getFirstName(),
                employeeBodyReq.getLastName(),
                employeeBodyReq.getEmail(),
                employeeBodyReq.getDateOfBirth(),
                employeeBodyReq.getGender(),
                employeeBodyReq.getHireDate(),
                departmentDto.get(),
                positionDto.get(),
                employeeBodyReq.getSalary()
        );
        int result = employeeServices.addEmployee(employeeDto);
        if (result == -1) {
            return "Failed to add employee,please enter unique email";
        }
        return "Employee added successfully";
    }

    @WebResult(name="updateEmployee")
    public String updateEmployee(EmployeeBodyReq employeeBodyReq ,@WebParam(name="id") int id){
        Optional<DepartmentDto> departmentDto = new DepartmentServices().getDepartmentById(employeeBodyReq.getDepartmentId());
        Optional<PositionDto> positionDto = new PositionServices().getPositionById(employeeBodyReq.getPositionId());
        if(!departmentDto.isPresent()){
            return "Department not found";
        }
        if(!positionDto.isPresent()){
            return "Position not found";
        }
        EmployeeDto employeeDto = new EmployeeDto(
                id,
                employeeBodyReq.getFirstName(),
                employeeBodyReq.getLastName(),
                employeeBodyReq.getEmail(),
                employeeBodyReq.getDateOfBirth(),
                employeeBodyReq.getGender(),
                employeeBodyReq.getHireDate(),
                departmentDto.get(),
                positionDto.get(),
                employeeBodyReq.getSalary()
        );
        System.out.println("malllllk");
        Optional<EmployeeDto> result = employeeServices.getEmployeeById(id);
        System.out.println("lollllllllllllll");
        if (!result.isPresent()) {
            return "Failed to update employee,please enter a correct id";
        }
        System.out.println(employeeDto);
        employeeServices.updateEmployee(employeeDto);
        System.out.println(employeeDto);
        return "Updated employee successfully";

    }


    @WebResult(name = "AddressByEmployeeId")
    public AddressDto getAddressByEmployeeId(@WebParam(name="id") int id){
        Optional<AddressDto> addressDto = employeeServices.getAddressByEmployeeId(id);
        if(!addressDto.isPresent()){
            throw new RuntimeException( "this employee id has no address found");
        }
        else{
            return addressDto.get();
        }
    }

    @WebResult(name="AttendanceInYearByEmployeeId")
    public List<AttendanceDto> getAttendanceInYearByEmployeeId(@WebParam(name="id") int id, @WebParam(name = "year") int year){
        List<AttendanceDto> attendanceDtos = employeeServices.getAttendanceInYearByEmployeeId(id,year);
       return attendanceDtos;
    }


}

// set CATALINA_HOME=C:\Users\reems\Downloads\apache-tomcat-10.1.18-windows-x64\apache-tomcat-10.1.18
// cd %CATALINA_HOME%\bin
// startup.bat