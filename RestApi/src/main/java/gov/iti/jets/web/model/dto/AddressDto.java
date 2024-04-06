package gov.iti.jets.web.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.iti.jets.web.persistence.entities.Employee;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Address}
 */
@Value
public class AddressDto implements Serializable {
    Integer id;
    @Size(max = 100)
    String street;
    @Size(max = 50)
    String city;
    @Size(max = 20)
    String postalCode;
    @NotNull
    EmployeeDto employee;
}