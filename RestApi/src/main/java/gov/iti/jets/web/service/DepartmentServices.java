package gov.iti.jets.web.service;

import gov.iti.jets.web.model.dto.DepartmentDto;
import gov.iti.jets.web.mapper.DepartmentMapper;
import gov.iti.jets.web.persistence.connection.DB;
import gov.iti.jets.web.persistence.entities.Department;
import gov.iti.jets.web.persistence.repository.DepartmentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentServices {
    public  List<DepartmentDto> getAllDepartments(){
        return DB.doInTransaction(em->{
            DepartmentRepo departmentRepo = new DepartmentRepo(em);
            List<DepartmentDto> result= new ArrayList<>();
            for(Department department:departmentRepo.findAll().get()) {
                result.add(DepartmentMapper.INSTANCE.toDto(department));
            }
            return result;
        });
    }

    public  Optional<DepartmentDto> getDepartmentById(Integer departmentId){
        return DB.doInTransaction(em->{
            DepartmentRepo departmentRepo = new DepartmentRepo(em);
            Optional<Department> department = departmentRepo.findById(departmentId);
            if(department.isPresent()){
                return  Optional.of(DepartmentMapper.INSTANCE.toDto(department.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  Optional<DepartmentDto> updateDepartment(DepartmentDto departmentDto){
        return DB.doInTransaction(em->{
            DepartmentRepo departmentRepo = new DepartmentRepo(em);
            Optional<Department> department = departmentRepo.update(DepartmentMapper.INSTANCE.toEntity(departmentDto));
            if(department.isPresent()){
                return  Optional.of(DepartmentMapper.INSTANCE.toDto(department.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  int deleteDepartmentById(Integer departmentId){
        Optional<DepartmentDto> departmentFound = getDepartmentById(departmentId);
        if(departmentFound.isPresent()){
            DB.doInTransactionWithoutResult(em->{
                DepartmentRepo departmentRepo = new DepartmentRepo(em);
                departmentRepo.deleteById(departmentId);
            });
            return 1;
        }
        else{
            return 0;
        }
    }


    public  void addDepartment(DepartmentDto departmentDto){
        DB.doInTransactionWithoutResult(em->{
            DepartmentRepo departmentRepo = new DepartmentRepo(em);
            departmentRepo.create(DepartmentMapper.INSTANCE.toEntity(departmentDto));
        });
    }
}
