package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.*;
import gov.iti.jets.web.model.requestBody.EmployeeBodyReq;
import gov.iti.jets.web.service.DepartmentServices;
import gov.iti.jets.web.service.EmployeeServices;
import gov.iti.jets.web.service.PositionServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Optional;

@Path("employees/v1")
public class EmployeeResource {

    private EmployeeServices employeeServices;
    public EmployeeResource(){
        employeeServices = new EmployeeServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(@Context UriInfo uriInfo){
        List<EmployeeDto> employeeDtoList = employeeServices.getAllEmployees();
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(employeeDtoList).links(link).build();
    }


    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") int id,@Context UriInfo uriInfo){
        Optional<EmployeeDto> employeeDto = employeeServices.getEmployeeById(id);
        if(employeeDto.isPresent()){
            Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok(employeeDto.get()).links(link).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("No employee with this id").build();
        }
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response addEmployee(EmployeeBodyReq employeeBodyReq){
        Optional<DepartmentDto> departmentDto = new DepartmentServices().getDepartmentById(employeeBodyReq.getDepartmentId());
        Optional<PositionDto> positionDto = new PositionServices().getPositionById(employeeBodyReq.getPositionId());
        if(!departmentDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Department not found").build();
        }
        if(!positionDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Position not found").build();
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
            return Response.serverError().entity("Failed to add employee,please enter unique email").build();
        }
        return Response.status(Response.Status.CREATED).entity("Employee added successfully").build();
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public Response updateEmployee(EmployeeBodyReq employeeBodyReq ,@PathParam("id") int id){
        Optional<DepartmentDto> departmentDto = new DepartmentServices().getDepartmentById(employeeBodyReq.getDepartmentId());
        Optional<PositionDto> positionDto = new PositionServices().getPositionById(employeeBodyReq.getPositionId());
        if(!departmentDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Department not found").build();
        }
        if(!positionDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Position not found").build();
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
        Optional<EmployeeDto> result = employeeServices.getEmployeeById(id);
        if (!result.isPresent()) {
            employeeServices.updateEmployee(employeeDto);
            return Response.serverError().entity("Failed to update employee,please enter a correct id").build();
        }
        return Response.status(Response.Status.OK).entity(employeeDto).build();

    }


    @GET
    @Path("address/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressByEmployeeId(@Context UriInfo uriInfo,@PathParam("id") int id){
        System.out.println("address");
        Optional<AddressDto> addressDto = employeeServices.getAddressByEmployeeId(id);
        if(!addressDto.isPresent()){
            Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.status(Response.Status.NOT_FOUND).links(link).entity("this employee id has no address found").build();
        }
        else{
            return Response.status(Response.Status.OK).entity(addressDto.get()).build();
        }
    }

    @GET
    @Path("attendance/{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttendanceInYearByEmployeeId(@Context UriInfo uriInfo,@PathParam("id") int id, @QueryParam("year") int year){
        List<AttendanceDto> attendanceDtos = employeeServices.getAttendanceInYearByEmployeeId(id,year);
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.status(Response.Status.OK).links(link).entity(attendanceDtos).build();
    }


}

// set CATALINA_HOME=C:\Users\reems\Downloads\apache-tomcat-10.1.18-windows-x64\apache-tomcat-10.1.18
// cd %CATALINA_HOME%\bin
// startup.bat