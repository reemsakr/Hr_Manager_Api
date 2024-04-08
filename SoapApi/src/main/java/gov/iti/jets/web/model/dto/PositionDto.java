package gov.iti.jets.web.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.jets.web.persistence.entities.Position}
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PositionDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String title;
    String description;
}