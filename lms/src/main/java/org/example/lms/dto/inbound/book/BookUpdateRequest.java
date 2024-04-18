package org.example.lms.dto.inbound.book;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.example.lms.enums.BookStatusEnum;
import org.example.lms.models.BorrowingRecord;
import org.example.lms.validtors.AuthorName;
import org.example.lms.validtors.BookName;
import org.example.lms.validtors.BookStatus;
import org.example.lms.validtors.ID;
import org.example.lms.validtors.ISBN;
import org.example.lms.validtors.Name;
import org.example.lms.validtors.YearFormat;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateRequest {
    @NotNull
    @JsonProperty("id")
    @ID
    private String id;
    @NotNull
    @JsonProperty("author")
    @AuthorName
    private String author;

    @NotNull
    @JsonProperty("title")
    @BookName
    private String title;

    @NotNull
    @JsonProperty("isbn")
    @ISBN
    private String isbn;

    @NotNull
    @JsonProperty("publicationYear")
    @YearFormat
    private String publicationYear;

    @NotNull
    @JsonProperty("status")
    @BookStatus
    private String status;
}
