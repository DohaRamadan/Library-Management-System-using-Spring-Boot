package org.example.lms.dto.outbound.patron;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatronUpdateResponse {
    @JsonProperty("patron")
    PatronResponse patronResponse;
}
