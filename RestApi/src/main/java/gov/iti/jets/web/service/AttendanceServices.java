package gov.iti.jets.web.service;

import gov.iti.jets.web.model.dto.AttendanceDto;
import gov.iti.jets.web.mapper.AttendanceMapper;
import gov.iti.jets.web.persistence.connection.DB;
import gov.iti.jets.web.persistence.entities.Attendance;
import gov.iti.jets.web.persistence.repository.AttendanceRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceServices {
    public  List<AttendanceDto> getAllAttendances(){
        return DB.doInTransaction(em->{
            AttendanceRepo attendanceRepo = new AttendanceRepo(em);
            List<AttendanceDto> result= new ArrayList<>();
            for(Attendance attendance:attendanceRepo.findAll().get()) {
                result.add(AttendanceMapper.INSTANCE.toDto(attendance));
            }
            return result;
        });
    }

    public  Optional<AttendanceDto> getAttendanceById(Integer attendanceId){
        return DB.doInTransaction(em->{
            AttendanceRepo attendanceRepo = new AttendanceRepo(em);
            Optional<Attendance> attendance = attendanceRepo.findById(attendanceId);
            if(attendance.isPresent()){
                return  Optional.of(AttendanceMapper.INSTANCE.toDto(attendance.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  Optional<AttendanceDto> updateAttendance(AttendanceDto attendanceDto){
        return DB.doInTransaction(em->{
            AttendanceRepo attendanceRepo = new AttendanceRepo(em);
            Optional<Attendance> attendance = attendanceRepo.update(AttendanceMapper.INSTANCE.toEntity(attendanceDto));
            if(attendance.isPresent()){
                return  Optional.of(AttendanceMapper.INSTANCE.toDto(attendance.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  int deleteAttendanceById(Integer attendanceId){
        Optional<AttendanceDto> attendanceFound = getAttendanceById(attendanceId);
        if(attendanceFound.isPresent()){
            DB.doInTransactionWithoutResult(em->{
                AttendanceRepo attendanceRepo = new AttendanceRepo(em);
                attendanceRepo.deleteById(attendanceId);
            });
            return 1;
        }
        else{
            return 0;
        }
    }


    public  void addAttendance(AttendanceDto attendanceDto){
        DB.doInTransactionWithoutResult(em->{
            AttendanceRepo attendanceRepo = new AttendanceRepo(em);
            attendanceRepo.create(AttendanceMapper.INSTANCE.toEntity(attendanceDto));
        });
    }
}
