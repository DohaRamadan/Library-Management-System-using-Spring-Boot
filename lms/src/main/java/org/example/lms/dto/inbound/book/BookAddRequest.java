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
    private String author;

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("isbn")
    private String isbn;

    @NotNull
    @JsonProperty("publicationYear")
    private String publicationYear;

    @NotNull
    @JsonProperty("status")
    private BookStatusEnum status;
}
