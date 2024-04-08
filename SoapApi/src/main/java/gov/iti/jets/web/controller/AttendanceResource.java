package gov.iti.jets.web.controller;

import gov.iti.jets.web.model.dto.AttendanceDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.requestBody.AttendanceBodyReq;
import gov.iti.jets.web.service.AttendanceServices;
import gov.iti.jets.web.service.EmployeeServices;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@WebService
public class AttendanceResource {

    private AttendanceServices attendanceServices;
    public AttendanceResource(){
        attendanceServices = new AttendanceServices();
    }

    @WebResult(name = "addresses")
    public List<AttendanceDto> getAllAttendances(){
        List<AttendanceDto> attendanceDtos = attendanceServices.getAllAttendances();

        return attendanceDtos;
    }

    @WebResult(name = "address")
    public AttendanceDto getAttendanceById(@WebParam(name = "id") int id){
        Optional<AttendanceDto> attendanceDto = attendanceServices.getAttendanceById(id);
        if(attendanceDto.isPresent()){

            return attendanceDto.get();
        }
        else{
            throw new RuntimeException("Wrong credentials");
        }
    }

    @WebResult(name = "addAddress")
    public String addAttendance(AttendanceBodyReq attendanceBodyReq){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(attendanceBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return "Employee not found";
        }
        System.out.println("llolol "+attendanceBodyReq.getAttendanceDate());
        AttendanceDto attendanceDto = new AttendanceDto(
                null,
                attendanceBodyReq.getAttendanceDate(),
                LocalTime.parse(attendanceBodyReq.getTimeIn()),
                LocalTime.parse(attendanceBodyReq.getTimeOut()),
                employeeDto.get()
        );
        attendanceServices.addAttendance(attendanceDto);

        return "Attendance added successfully";
    }

    @WebResult(name="updateAttendance")
    public String updateAttendance(AttendanceBodyReq attendanceBodyReq,@WebParam(name = "id") int id){
        Optional<EmployeeDto> employeeDto = new EmployeeServices().getEmployeeById(attendanceBodyReq.getEmployeeId());
        if(!employeeDto.isPresent()){
            return "Employee not found";
        }
        AttendanceDto attendanceDto = new AttendanceDto(
                id,
                attendanceBodyReq.getAttendanceDate(),
                LocalTime.parse(attendanceBodyReq.getTimeIn()),
                LocalTime.parse(attendanceBodyReq.getTimeOut()),
                employeeDto.get()
        );
        if(attendanceServices.getAttendanceById(id).isPresent()) {
            attendanceServices.updateAttendance(attendanceDto);
            return "Updated added successfully";
        }
        else{
            return "Failed to update attendance,please enter a correct id";
        }
    }

    @WebResult(name="deleteAttendance")
    public String deleteAttendanceById(@WebParam(name = "id")int id){
        int result = attendanceServices.deleteAttendanceById(id);
        if(result==-1){
            return "Attendance with this id is not found";
        }
        else{
            return "deleted successfully";
        }
    }
}
