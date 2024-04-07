package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.AddressDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.AddressBodyReq;
import gov.iti.jets.web.service.AddressServices;
import gov.iti.jets.web.service.EmployeeServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;
import java.util.Optional;

@Path("addresses/v1")
public class AddressResource {

    private AddressServices addressServices;
    public AddressResource(){
        addressServices = new AddressServices();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddresses(@Context UriInfo uriInfo){
        List<AddressDto> addressDtos = addressServices.getAllAddresses();
        Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        return Response.ok(addressDtos).links(link).build();
    }

    @GET
    @Path("{id:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressById(@PathParam("id") int id, @Context UriInfo uriInfo){
        System.out.println("address id");
        Optional<AddressDto> addressDto = addressServices.getAddressById(id);
        if(addressDto.isPresent()){
            Link link = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
            return Response.ok(addressDto.get()).links(link).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).entity("No address with this id").build();
        }
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response addAddress(AddressBodyReq addressBodyReq){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(addressBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Employee not found").build();
        }
        AddressDto addressDto = new AddressDto(
                null,
                addressBodyReq.getStreet(),
                addressBodyReq.getCity(),
                addressBodyReq.getPostalCode(),
                employeeDto.get()
        );
        addressServices.addAddress(addressDto);

        return Response.status(Response.Status.CREATED).entity("Address added successfully").build();
    }

    @PUT
    @Path("{id:[0-9]+}")
    @Consumes({"application/xml", "application/json"})
    public Response updateAddress(AddressBodyReq addressBodyReq,@PathParam("id") int id){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(addressBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Employee not found").build();
        }
        AddressDto addressDto = new AddressDto(
                id,
                addressBodyReq.getStreet(),
                addressBodyReq.getCity(),
                addressBodyReq.getPostalCode(),
                employeeDto.get()
        );
        if(addressServices.getAddressById(id).isPresent()) {
           addressServices.updateAddress(addressDto);
            return Response.status(Response.Status.OK).entity("Updated added successfully").build();
        }
        else{
            return Response.serverError().entity("Failed to update address,please enter a correct id").build();
        }
    }

    @DELETE
    @Path("{id:[0-9]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteAddressById(@PathParam("id")int id){
        int result = addressServices.deleteAddressById(id);
        if(result==-1){
            return Response.status(Response.Status.NOT_FOUND).entity("Address with this id is not found").build();
        }
        else{
            return Response.status(Response.Status.OK).entity("deleted successfully").build();
        }
    }
}
