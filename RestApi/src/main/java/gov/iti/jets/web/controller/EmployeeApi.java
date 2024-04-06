package gov.iti.jets.web.controller;

import gov.iti.jets.web.dto.EmployeeDto;
import gov.iti.jets.web.service.EmployeeServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("employee")
public class EmployeeApi {

    private EmployeeServices employeeServices;
    public EmployeeApi(){
        employeeServices = new EmployeeServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(@Context UriInfo uriInfo){
        List<EmployeeDto> employeeDtoList = employeeServices.getAllEmployees();
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(employeeDtoList).links(link).build();
    }
}

// set CATALINA_HOME=C:\Users\reems\Downloads\apache-tomcat-10.1.18-windows-x64\apache-tomcat-10.1.18
// cd %CATALINA_HOME%\bin
// startup.bat