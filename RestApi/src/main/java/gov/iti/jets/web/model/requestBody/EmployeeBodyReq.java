package gov.iti.jets.web.model.requestBody;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeBodyReq implements Serializable {

    @Size(max = 255)
    private  String firstName;

    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String email;

    private LocalDate dateOfBirth;

    private String gender;

    private LocalDate hireDate;

    private Integer departmentId;

    private Integer positionId;

    private BigDecimal salary;
}
