package org.example.lms.dto.outbound.patron;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lms.models.Patron;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatronGetResponse {
    private PatronResponse patronResponse;

    public PatronGetResponse(Patron patron){
        this.patronResponse = new PatronResponse(patron);
    }
}
