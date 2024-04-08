package gov.iti.jets.web.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Attendance}
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDto implements Serializable {
    Integer id;
    @NotNull
    Date attendanceDate;
    @NotNull
    LocalTime timeIn;
    @NotNull
    LocalTime timeOut;
    @NotNull
    EmployeeDto employee;
}