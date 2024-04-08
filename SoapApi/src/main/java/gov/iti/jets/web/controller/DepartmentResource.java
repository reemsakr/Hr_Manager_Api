package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.DepartmentBodyReq;
import gov.iti.jets.web.service.DepartmentServices;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Optional;

@WebService
public class DepartmentResource {
    private DepartmentServices departmentServices;
    public DepartmentResource(){
        departmentServices = new DepartmentServices();
    }

    @WebResult(name="departments")
    public List<DepartmentDto> getAllDepartments(){
        List<DepartmentDto> departmentDtos = departmentServices.getAllDepartments();
        return departmentDtos;
    }

    @WebResult(name = "department")
    public DepartmentDto getDepartmentById(@WebParam(name="id") int id){
        Optional<DepartmentDto> departmentDto = departmentServices.getDepartmentById(id);
        if(departmentDto.isPresent()){
            return departmentDto.get();
        }
        else{
            throw new RuntimeException("Wrong credentials");
        }
    }

    @WebResult(name = "addDepartment")
    public String addDepartment(DepartmentBodyReq departmentBodyReq){
        DepartmentDto departmentDto = new DepartmentDto(null,departmentBodyReq.getName());
        departmentServices.addDepartment(departmentDto);
        return "Department added successfully";
    }

    @WebResult(name="updateDepartment")
    public String updateDepartment(DepartmentBodyReq departmentBodyReq,@WebParam(name="id") int id){

        if(departmentServices.getDepartmentById(id).isPresent()) {
            DepartmentDto departmentDto= new DepartmentDto(id,departmentBodyReq.getName());
            departmentServices.updateDepartment(departmentDto);
            return "Updated added successfully";
        }
        else{
            return "Failed to update Department,please enter a correct id";
        }
    }

    @WebResult(name = "AllEmployeeByDepartmentId")
    public List<EmployeeDto> getAllEmployeeByDepartmentId(@WebParam(name="id") int id){
        Optional<DepartmentDto> departmentDto = departmentServices.getDepartmentById(id);
        if(departmentDto.isPresent()){
            List<EmployeeDto> employeeDtos = departmentServices.getAllEmployeeByDepartmentId(id);
            return employeeDtos;
        }else{
            throw new RuntimeException("Wrong credentials");
        }

    }

}
