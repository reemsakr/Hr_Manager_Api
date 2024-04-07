package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.AttendanceDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.AttendanceBodyReq;
import gov.iti.jets.web.service.AttendanceServices;
import gov.iti.jets.web.service.EmployeeServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Optional;

@Path("attendances/v1")
public class AttendanceResource {

    private AttendanceServices attendanceServices;
    public AttendanceResource(){
        attendanceServices = new AttendanceServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAttendances(@Context UriInfo uriInfo){
        List<AttendanceDto> attendanceDtos = attendanceServices.getAllAttendances();
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(attendanceDtos).links(link).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttendanceById(@PathParam("id") int id, @Context UriInfo uriInfo){
        Optional<AttendanceDto> attendanceDto = attendanceServices.getAttendanceById(id);
        if(attendanceDto.isPresent()){
            Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok(attendanceDto.get()).links(link).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("No Attendance with this id").build();
        }
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response addAttendance(AttendanceBodyReq attendanceBodyReq){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(attendanceBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Employee not found").build();
        }
        AttendanceDto attendanceDto = new AttendanceDto(
                null,
                attendanceBodyReq.getAttendanceDate(),
                attendanceBodyReq.getTimeIn(),
                attendanceBodyReq.getTimeOut(),
                employeeDto.get()
        );
        attendanceServices.addAttendance(attendanceDto);

        return Response.status(Response.Status.CREATED).entity("Attendance added successfully").build();
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public Response updateAttendance(AttendanceBodyReq attendanceBodyReq,@PathParam("id") int id){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(attendanceBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Employee not found").build();
        }
        AttendanceDto attendanceDto = new AttendanceDto(
                id,
                attendanceBodyReq.getAttendanceDate(),
                attendanceBodyReq.getTimeIn(),
                attendanceBodyReq.getTimeOut(),
                employeeDto.get()
        );
        if(attendanceServices.getAttendanceById(id).isPresent()) {
            attendanceServices.updateAttendance(attendanceDto);
            return Response.status(Response.Status.OK).entity("Updated added successfully").build();
        }
        else{
            return Response.serverError().entity("Failed to update attendance,please enter a correct id").build();
        }
    }

    @DELETE
    @Path("{id:[0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAttendanceById(@PathParam("id")int id){
        int result = attendanceServices.deleteAttendanceById(id);
        if(result==-1){
            return Response.status(Response.Status.NOT_FOUND).entity("Attendance with this id is not found").build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).entity("deleted successfully").build();
        }
    }
}
