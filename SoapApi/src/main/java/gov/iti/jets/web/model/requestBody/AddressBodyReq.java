package gov.iti.jets.web.model.requestBody;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressBodyReq implements Serializable {
    @Size(max = 100)
    String street;
    @Size(max = 50)
    String city;
    @Size(max = 20)
    String postalCode;
    @NotNull
    Integer employeeId;
}
