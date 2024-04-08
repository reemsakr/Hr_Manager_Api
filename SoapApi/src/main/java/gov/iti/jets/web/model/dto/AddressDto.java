package gov.iti.jets.web.model.dto;
;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Address}
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
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