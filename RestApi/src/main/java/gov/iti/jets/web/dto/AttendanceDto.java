package gov.iti.jets.web.dto;

import gov.iti.jets.web.persistence.entities.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Attendance}
 */
@Value
public class AttendanceDto implements Serializable {
    Integer id;
    @NotNull
    Date attendanceDate;
    @NotNull
    LocalTime timeIn;
    @NotNull
    LocalTime timeOut;
    @NotNull
    Employee employee;
}