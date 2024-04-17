package org.example.lms.dto.inbound.book;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.lms.enums.BookStatusEnum;
import org.example.lms.models.BorrowingRecord;
import org.example.lms.validtors.BookName;
import org.example.lms.validtors.BookStatus;
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
public class BookAddRequest {
    @NotNull
    @JsonProperty("author")
    @Name
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
