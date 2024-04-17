package org.example.lms.dto.outbound.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lms.models.Book;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookGetResponse {
    @JsonProperty("book")
    BookResponse book;

    public BookGetResponse(Book book) {
        this.book = new BookResponse(book);
    }
}
