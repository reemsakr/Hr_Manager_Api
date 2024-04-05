package gov.iti.jets.web.dto;

import gov.iti.jets.web.persistence.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Employee}
 */
@Value
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
    LocalDate dateOfBirth;
    @NotNull
    Gender gender;
    @NotNull
    LocalDate hireDate;
    @NotNull
    DepartmentDto department;
    @NotNull
    PositionDto position;
    @NotNull
    BigDecimal salary;

}