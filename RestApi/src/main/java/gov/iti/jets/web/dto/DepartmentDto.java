package gov.iti.jets.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Department}
 */
@Value
public class DepartmentDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String name;
}