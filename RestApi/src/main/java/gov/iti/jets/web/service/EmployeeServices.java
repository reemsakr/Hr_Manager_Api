package gov.iti.jets.web.service;

import gov.iti.jets.web.mapper.AddressMapper;
import gov.iti.jets.web.mapper.AttendanceMapper;
import gov.iti.jets.web.model.dto.AddressDto;
import gov.iti.jets.web.model.dto.AttendanceDto;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.mapper.EmployeeMapper;
import gov.iti.jets.web.persistence.connection.DB;
import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.entities.Attendance;
import gov.iti.jets.web.persistence.entities.Employee;
import gov.iti.jets.web.persistence.repository.EmployeeRepo;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServices {

    public  List<EmployeeDto> getAllEmployees(){
        return DB.doInTransaction(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            List<EmployeeDto> result= new ArrayList<>();
            for(Employee employee:employeeRepo.findAll().get()) {
                result.add(EmployeeMapper.INSTANCE.toDto(employee));
            }
            return result;
        });
    }

    public  Optional<EmployeeDto> getEmployeeById(Integer employeeId){
        return DB.doInTransaction(em->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Optional<Employee> employee = employeeRepo.findById(employeeId);
            if(employee.isPresent()){
                return  Optional.of(EmployeeMapper.INSTANCE.toDto(employee.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  Optional<EmployeeDto> updateEmployee(EmployeeDto employeeDto){
            return DB.doInTransaction(em->{
                EmployeeRepo employeeRepo = new EmployeeRepo(em);
                Optional<Employee> employee = employeeRepo.update(EmployeeMapper.INSTANCE.toEntity(employeeDto));
                if(employee.isPresent()){
                    return  Optional.of(EmployeeMapper.INSTANCE.toDto(employee.get()));
                }
                else{
                    return Optional.empty();
                }
            });
    }

    public  int deleteEmployeeById(Integer employeeId){
        Optional<EmployeeDto> employeeFound = getEmployeeById(employeeId);
        if(employeeFound.isPresent()){
             DB.doInTransactionWithoutResult(em->{
                 EmployeeRepo employeeRepo = new EmployeeRepo(em);
                 employeeRepo.deleteById(employeeId);
             });
             return 1;
        }
        else{
            return 0;
        }
    }

    public  boolean isEmailFound(String email){
        return DB.doInTransaction(em ->{
            EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Optional<Employee> employee = employeeRepo.getEmployeeByEmail(email);
            if(employee.isPresent())
                return true;
            else
                return false;
        });
    }
    public  int addEmployee(EmployeeDto employeeDto){
        if(!isEmailFound(employeeDto.getEmail())){
            DB.doInTransactionWithoutResult(em->{
                EmployeeRepo employeeRepo = new EmployeeRepo(em);
                employeeRepo.create(EmployeeMapper.INSTANCE.toEntity(employeeDto));
            });
            return 1;
        }
        else{
            return -1;
        }
    }

    public Optional<AddressDto> getAddressByEmployeeId(Integer employeeId){
        return DB.doInTransaction(em->{
           EmployeeRepo employeeRepo = new EmployeeRepo(em);
            Optional<Address> addressByEmployeeId = employeeRepo.getAddressByEmployeeId(employeeId);

            if(addressByEmployeeId.isPresent()){
                return Optional.of(AddressMapper.INSTANCE.toDto(addressByEmployeeId.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public List<AttendanceDto> getAttendanceInYearByEmployeeId(Integer employeeId,int year){
        return DB.doInTransaction(em->{
           EmployeeRepo employeeRepo = new EmployeeRepo(em);
           List<Attendance> attendances = employeeRepo.getAttendanceInYearByEmployeeId(employeeId,year);
           List<AttendanceDto> attendanceDtos = new ArrayList<>();
           for(Attendance attendance :attendances){
               attendanceDtos.add(AttendanceMapper.INSTANCE.toDto(attendance));
           }
           return attendanceDtos;
        });
    }
}
