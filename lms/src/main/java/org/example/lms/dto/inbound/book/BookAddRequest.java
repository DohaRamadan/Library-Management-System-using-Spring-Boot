package org.example.lms.dto.inbound.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lms.validtors.AuthorName;
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
