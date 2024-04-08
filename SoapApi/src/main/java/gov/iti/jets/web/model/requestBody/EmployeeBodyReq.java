package gov.iti.jets.web.model.requestBody;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

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

    private Date dateOfBirth;

    private String gender;

    private Date hireDate;

    private Integer departmentId;

    private Integer positionId;

    private BigDecimal salary;
}
