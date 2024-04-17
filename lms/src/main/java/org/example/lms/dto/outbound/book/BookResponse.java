package org.example.lms.dto.outbound.book;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.lms.enums.BookStatusEnum;
import org.example.lms.models.Book;
import org.example.lms.models.BorrowingRecord;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {
    private Long id;
    private String author;
    private String title;
    private String isbn;
    private String publicationYear;
    private BookStatusEnum status;
    private List<BorrowingRecord> borrowingRecords;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.status = book.getStatus();
        this.title = book.getTitle();
        this.borrowingRecords = book.getBorrowingRecords();
        this.publicationYear = book.getPublicationYear();
    }
}
