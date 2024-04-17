package org.example.lms.dto.inbound.patron;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatronAddRequest {
    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotNull
    @JsonProperty("email")
    private String email;
}