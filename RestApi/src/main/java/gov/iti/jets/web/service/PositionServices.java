package gov.iti.jets.web.service;

import gov.iti.jets.web.mapper.EmployeeMapper;
import gov.iti.jets.web.model.dto.EmployeeDto;
import gov.iti.jets.web.model.dto.PositionDto;
import gov.iti.jets.web.mapper.PositionMapper;
import gov.iti.jets.web.persistence.connection.DB;
import gov.iti.jets.web.persistence.entities.Employee;
import gov.iti.jets.web.persistence.entities.Position;
import gov.iti.jets.web.persistence.repository.DepartmentRepo;
import gov.iti.jets.web.persistence.repository.PositionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionServices {
    public  List<PositionDto> getAllPositions(){
        return DB.doInTransaction(em->{
            PositionRepo positionRepo = new PositionRepo(em);
            List<PositionDto> result= new ArrayList<>();
            for(Position position:positionRepo.findAll().get()) {
                result.add(PositionMapper.INSTANCE.toDto(position));
            }
            return result;
        });
    }

    public  Optional<PositionDto> getPositionById(Integer positionId){
        return DB.doInTransaction(em->{
            PositionRepo positionRepo = new PositionRepo(em);
            Optional<Position> position = positionRepo.findById(positionId);
            if(position.isPresent()){
                return  Optional.of(PositionMapper.INSTANCE.toDto(position.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  Optional<PositionDto> updatePosition(PositionDto positionDto){
        return DB.doInTransaction(em->{
            PositionRepo positionRepo = new PositionRepo(em);
            Optional<Position> position = positionRepo.update(PositionMapper.INSTANCE.toEntity(positionDto));
            if(position.isPresent()){
                return  Optional.of(PositionMapper.INSTANCE.toDto(position.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  int deletePositionById(Integer positionId){
        Optional<PositionDto> positionFound = getPositionById(positionId);
        if(positionFound.isPresent()){
            DB.doInTransactionWithoutResult(em->{
                PositionRepo positionRepo = new PositionRepo(em);
                positionRepo.deleteById(positionId);
            });
            return 1;
        }
        else{
            return 0;
        }
    }


    public static void addPosition(PositionDto positionDto){
        DB.doInTransactionWithoutResult(em->{
            PositionRepo positionRepo = new PositionRepo(em);
            positionRepo.create(PositionMapper.INSTANCE.toEntity(positionDto));
        });
    }
    public List<EmployeeDto> getAllEmployeeByPositiontId(Integer positionId){
        return DB.doInTransaction(em->{
            PositionRepo positionRepo = new PositionRepo(em);
            List<Employee> employees = positionRepo.getAllEmployeeByPositionId(positionId);
            List<EmployeeDto> employeeDtos = new ArrayList<>();
            for(Employee employee : employees){
                employeeDtos.add(EmployeeMapper.INSTANCE.toDto(employee));
            }
            return employeeDtos;
        });
    }
}
