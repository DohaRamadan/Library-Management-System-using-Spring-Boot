package org.example.lms.dto.inbound.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.example.lms.validtors.Email;
import org.example.lms.validtors.Name;
import org.example.lms.validtors.PhoneNumber;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @NotNull
    @JsonProperty("email")
    @Email
    private String email;

    @NotNull
    @JsonProperty("password")
    // TODO
    private String password;
}
