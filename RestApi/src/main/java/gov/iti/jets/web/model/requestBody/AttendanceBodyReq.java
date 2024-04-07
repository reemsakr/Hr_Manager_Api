package gov.iti.jets.web.model.requestBody;

import gov.iti.jets.web.model.dto.EmployeeDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceBodyReq implements Serializable {
    Date attendanceDate;
    @NotNull
    LocalTime timeIn;
    @NotNull
    LocalTime timeOut;
    @NotNull
    Integer employeeId;
}
