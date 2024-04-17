package org.example.lms.dto.outbound.patron;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatronDeleteResponse {
}
