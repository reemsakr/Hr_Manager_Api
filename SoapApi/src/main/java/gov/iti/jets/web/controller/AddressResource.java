package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.AddressDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.AddressBodyReq;
import gov.iti.jets.web.service.AddressServices;
import gov.iti.jets.web.service.EmployeeServices;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Optional;

@WebService
public class AddressResource {

    private AddressServices addressServices;
    public AddressResource(){
        addressServices = new AddressServices();
    }

    @WebResult(name="addresses")
    public List<AddressDto> getAllAddresses(){
        List<AddressDto> addressDtos = addressServices.getAllAddresses();
        return addressDtos;
    }

    @WebResult(name="address")
    public AddressDto getAddressById(@WebParam(name="id") int id){
        Optional<AddressDto> addressDto = addressServices.getAddressById(id);
        if(addressDto.isPresent()){

            return addressDto.get();
        }
        else{
            throw new RuntimeException("Wrong credentials");
        }
    }


    @WebResult(name = "addAddress")
    public String addAddress(@WebParam(name="address") AddressBodyReq addressBodyReq){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(addressBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return "Employee not found";
        }
        AddressDto addressDto = new AddressDto(
                null,
                addressBodyReq.getStreet(),
                addressBodyReq.getCity(),
                addressBodyReq.getPostalCode(),
                employeeDto.get()
        );
        addressServices.addAddress(addressDto);

        return "Address added successfully";
    }

    @WebResult(name = "updateAddress")
    public String updateAddress(@WebParam(name="address")AddressBodyReq addressBodyReq,@WebParam(name = "id") int id){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(addressBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return "Employee not found";
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
            return "Updated added successfully";
        }
        else{
            return "Failed to update address,please enter a correct id";
        }
    }


    @WebResult(name = "deleteAddress")
    public String deleteAddressById(@WebParam(name = "id")int id){
        int result = addressServices.deleteAddressById(id);
        if(result==-1){
            return "Address with this id is not found";
        }
        else{
            return "deleted successfully";
        }
    }
}
