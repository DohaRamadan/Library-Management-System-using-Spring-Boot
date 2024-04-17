package org.example.lms.dto.inbound.borrowingrecord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Getter
@Setter
@AllArgsConstructor
public class BorrowingRecordBorrowRequest {
    private Long bookId;
    private Long patronId;
}
