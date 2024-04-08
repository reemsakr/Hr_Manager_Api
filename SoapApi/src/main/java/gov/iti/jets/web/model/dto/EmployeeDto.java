package gov.iti.jets.web.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Employee}
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String firstName;
    @NotNull
    @Size(max = 255)
    String lastName;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    Date dateOfBirth;
    @NotNull
    String gender;
    @NotNull
    Date hireDate;
    @NotNull
    DepartmentDto department;
    @NotNull
    PositionDto position;
    @NotNull
    BigDecimal salary;

}