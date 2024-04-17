package org.example.lms.dto.outbound.patron;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lms.models.Patron;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatronsGetResponse {
    private List<PatronResponse> patronResponses;

    public PatronsGetResponse(List<Patron>patrons){
        this.patronResponses = patrons.stream().map(PatronResponse::new).collect(Collectors.toList());
    }
}
