package gov.iti.jets.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Position}
 */
@Value
public class PositionDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String title;
    String description;
}