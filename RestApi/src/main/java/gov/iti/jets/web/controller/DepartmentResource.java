package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.DepartmentBodyReq;
import gov.iti.jets.web.service.DepartmentServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Optional;

@Path("departments/v1")
public class DepartmentResource {
    private DepartmentServices departmentServices;
    public DepartmentResource(){
        departmentServices = new DepartmentServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDepartmentes(@Context UriInfo uriInfo){
        List<DepartmentDto> departmentDtos = departmentServices.getAllDepartments();
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(departmentDtos).links(link).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepartmentById(@PathParam("id") int id, @Context UriInfo uriInfo){
        Optional<DepartmentDto> departmentDto = departmentServices.getDepartmentById(id);
        if(departmentDto.isPresent()){
            Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok(departmentDto.get()).links(link).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("No Department with this id").build();
        }
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response addDepartment(DepartmentBodyReq departmentBodyReq){
        DepartmentDto departmentDto = new DepartmentDto(null,departmentBodyReq.getName());
        departmentServices.addDepartment(departmentDto);
        return Response.status(Response.Status.CREATED).entity("Department added successfully").build();
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public Response updateDepartment(DepartmentBodyReq departmentBodyReq,@PathParam("id") int id){

        if(departmentServices.getDepartmentById(id).isPresent()) {
            DepartmentDto departmentDto= new DepartmentDto(id,departmentBodyReq.getName());
            departmentServices.updateDepartment(departmentDto);
            return Response.status(Response.Status.OK).entity("Updated added successfully").build();
        }
        else{
            return Response.serverError().entity("Failed to update Department,please enter a correct id").build();
        }
    }

    @Path("{id:[0-9]+}/employees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployeeByDepartmentId(@PathParam("id") int id){
        Optional<DepartmentDto> departmentDto = departmentServices.getDepartmentById(id);
        if(departmentDto.isPresent()){
            List<EmployeeDto> employeeDtos = departmentServices.getAllEmployeeByDepartmentId(id);
            return Response.status(Response.Status.OK).entity(employeeDtos).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("No Department with this id").build();
        }

    }

}
