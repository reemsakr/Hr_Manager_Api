package gov.iti.jets.web.model.requestBody;

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
    String timeIn;
    @NotNull
    String timeOut;
    @NotNull
    Integer employeeId;
}
