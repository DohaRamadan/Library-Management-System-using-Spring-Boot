package org.example.lms.dto.inbound.borrowingrecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.example.lms.validtors.ID;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Getter
@Setter
@AllArgsConstructor
public class BorrowingRecordBorrowRequest {
    @ID
    @NotNull
    @JsonProperty("bookId")
    private String bookId;
    @ID
    @NotNull
    @JsonProperty("patronId")
    private String patronId;
}
