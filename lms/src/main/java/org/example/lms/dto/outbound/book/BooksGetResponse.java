package org.example.lms.dto.outbound.book;

import java.util.List;
import java.util.stream.Collectors;

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
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooksGetResponse {
    @JsonProperty("books")
    List<BookResponse> books;

    public BooksGetResponse(List<Book> books) {
        this.books = books.stream().map(BookResponse::new).collect(Collectors.toList());
    }
}
