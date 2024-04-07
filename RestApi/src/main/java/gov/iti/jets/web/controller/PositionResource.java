package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.dto.PositionDto;
import gov.iti.jets.web.model.requestBody.PositionBodyReq;
import gov.iti.jets.web.service.PositionServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Optional;

@Path("positions/v1")
public class PositionResource {
    private PositionServices positionServices;
    public PositionResource(){
        positionServices = new PositionServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPositions(@Context UriInfo uriInfo){
        List<PositionDto> positionDtos = positionServices.getAllPositions();
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(positionDtos).links(link).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPositionById(@PathParam("id") int id, @Context UriInfo uriInfo){
        Optional<PositionDto> positionDto = positionServices.getPositionById(id);
        if(positionDto.isPresent()){
            Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok(positionDto.get()).links(link).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("No Position with this id").build();
        }
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response addPosition(PositionBodyReq positionBodyReq){
        System.out.println("heeereeeeee");
        PositionDto positionDto = new PositionDto(null,positionBodyReq.getTitle(),positionBodyReq.getDescription());
        positionServices.addPosition(positionDto);
        return Response.status(Response.Status.CREATED).entity("Position added successfully").build();
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public Response updatePosition(PositionBodyReq positionBodyReq,@PathParam("id") int id){

        if(positionServices.getPositionById(id).isPresent()) {
            PositionDto positionDto= new PositionDto(id,positionBodyReq.getTitle(),positionBodyReq.getDescription());
            positionServices.updatePosition(positionDto);
            return Response.status(Response.Status.OK).entity("Updated added successfully").build();
        }
        else{
            return Response.serverError().entity("Failed to update Position,please enter a correct id").build();
        }
    }

    @Path("{id:[0-9]+}/employees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployeeByPositiontId(@PathParam("id") int id){
        Optional<PositionDto> positionDto = positionServices.getPositionById(id);
        if(positionDto.isPresent()){
            List<EmployeeDto> employeeDtos = positionServices.getAllEmployeeByPositiontId(id);
            return Response.status(Response.Status.OK).entity(employeeDtos).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("No Position with this id").build();
        }

    }
}
