package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.dto.PositionDto;
import gov.iti.jets.web.model.requestBody.PositionBodyReq;
import gov.iti.jets.web.service.PositionServices;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Optional;

@WebService
public class PositionResource {
    private PositionServices positionServices;
    public PositionResource(){
        positionServices = new PositionServices();
    }

    @WebResult(name = "positions")
    public List<PositionDto> getAllPositions(){
        List<PositionDto> positionDtos = positionServices.getAllPositions();
        return positionDtos;
    }

    @WebResult(name = "position")
    public PositionDto getPositionById(@WebParam(name = "id") int id){
        Optional<PositionDto> positionDto = positionServices.getPositionById(id);
        if(positionDto.isPresent()){
            return positionDto.get();
        }
        else{
            throw new RuntimeException("Wrong credentials");
        }
    }

    @WebResult(name="addPosition")
    public String addPosition(PositionBodyReq positionBodyReq){
        PositionDto positionDto = new PositionDto(null,positionBodyReq.getTitle(),positionBodyReq.getDescription());
        positionServices.addPosition(positionDto);
        return "Position added successfully";
    }

    @WebResult(name = "updatePosition")
    public String updatePosition(PositionBodyReq positionBodyReq,@WebParam(name = "id") int id){

        if(positionServices.getPositionById(id).isPresent()) {
            PositionDto positionDto= new PositionDto(id,positionBodyReq.getTitle(),positionBodyReq.getDescription());
            positionServices.updatePosition(positionDto);
            return "Updated added successfully";
        }
        else{
            return "Failed to update Position,please enter a correct id";
        }
    }

    @WebResult(name = "AllEmployeeByPositiontId")
    public List<EmployeeDto> getAllEmployeeByPositionId(@WebParam(name = "id") int id){
        Optional<PositionDto> positionDto = positionServices.getPositionById(id);
        if(positionDto.isPresent()){
            List<EmployeeDto> employeeDtos = positionServices.getAllEmployeeByPositiontId(id);
            return employeeDtos;
        }
        else{
            throw new RuntimeException("Wrong credentials");
        }

    }
}
