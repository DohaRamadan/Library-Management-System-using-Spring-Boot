package org.example.lms.dto.outbound.patron;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lms.models.BorrowingRecord;
import org.example.lms.models.Patron;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatronResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<BorrowingRecord> borrowingRecords;


    public PatronResponse(Patron patron){
        this.id = patron.getId();
        this.firstName = patron.getFirstName();
        this.lastName = patron.getLastName();
        this.email = patron.getEmail();
        this.phoneNumber = patron.getPhoneNumber();
        this.borrowingRecords = patron.getBorrowingRecords();
    }
}
