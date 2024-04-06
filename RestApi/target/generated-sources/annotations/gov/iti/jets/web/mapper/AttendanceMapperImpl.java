package gov.iti.jets.web.mapper;

import gov.iti.jets.web.dto.AttendanceDto;
import gov.iti.jets.web.persistence.entities.Attendance;
import gov.iti.jets.web.persistence.entities.Employee;
import java.time.LocalTime;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T01:45:08+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public Attendance toEntity(AttendanceDto attendanceDto) {
        if ( attendanceDto == null ) {
            return null;
        }

        Attendance attendance = new Attendance();

        attendance.setId( attendanceDto.getId() );
        attendance.setEmployee( attendanceDto.getEmployee() );
        attendance.setAttendanceDate( attendanceDto.getAttendanceDate() );
        attendance.setTimeIn( attendanceDto.getTimeIn() );
        attendance.setTimeOut( attendanceDto.getTimeOut() );

        return attendance;
    }

    @Override
    public AttendanceDto toDto(Attendance attendance) {
        if ( attendance == null ) {
            return null;
        }

        Integer id = null;
        Date attendanceDate = null;
        LocalTime timeIn = null;
        LocalTime timeOut = null;
        Employee employee = null;

        id = attendance.getId();
        attendanceDate = attendance.getAttendanceDate();
        timeIn = attendance.getTimeIn();
        timeOut = attendance.getTimeOut();
        employee = attendance.getEmployee();

        AttendanceDto attendanceDto = new AttendanceDto( id, attendanceDate, timeIn, timeOut, employee );

        return attendanceDto;
    }

    @Override
    public Attendance partialUpdate(AttendanceDto attendanceDto, Attendance attendance) {
        if ( attendanceDto == null ) {
            return attendance;
        }

        if ( attendanceDto.getId() != null ) {
            attendance.setId( attendanceDto.getId() );
        }
        if ( attendanceDto.getEmployee() != null ) {
            attendance.setEmployee( attendanceDto.getEmployee() );
        }
        if ( attendanceDto.getAttendanceDate() != null ) {
            attendance.setAttendanceDate( attendanceDto.getAttendanceDate() );
        }
        if ( attendanceDto.getTimeIn() != null ) {
            attendance.setTimeIn( attendanceDto.getTimeIn() );
        }
        if ( attendanceDto.getTimeOut() != null ) {
            attendance.setTimeOut( attendanceDto.getTimeOut() );
        }

        return attendance;
    }
}
