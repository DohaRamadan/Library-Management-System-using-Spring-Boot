package org.example.lms.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.lms.dto.inbound.book.BookAddRequest;
import org.example.lms.dto.inbound.book.BookDeleteRequest;
import org.example.lms.dto.inbound.book.BookGetRequest;
import org.example.lms.dto.inbound.book.BookUpdateRequest;
import org.example.lms.dto.inbound.book.BooksGetRequest;
import org.example.lms.dto.outbound.book.BookAddResponse;
import org.example.lms.dto.outbound.book.BookDeleteResponse;
import org.example.lms.dto.outbound.book.BookGetResponse;
import org.example.lms.dto.outbound.book.BookUpdateResponse;
import org.example.lms.dto.outbound.book.BooksGetResponse;
import org.example.lms.services.BookService;
import org.example.lms.validtors.ID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BooksGetResponse> getAllBooks() throws Exception {
        BooksGetRequest request = new BooksGetRequest();
        BooksGetResponse response = bookService.getAllBooks(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookGetResponse> getBookById(@Valid @ID @PathVariable(name = "bookId", required = true) @NotNull String bookId) throws Exception {
        BookGetRequest request = new BookGetRequest(bookId);
        BookGetResponse response = bookService.getBookById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookAddResponse> addBook(@Valid @RequestBody @NotNull BookAddRequest bookAddRequest) throws Exception {
        BookAddResponse response = bookService.addBook(bookAddRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookUpdateResponse> updateBook(@Valid @RequestBody @NotNull BookUpdateRequest bookUpdateRequest) throws Exception {
        BookUpdateResponse response = bookService.updateBook(bookUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookDeleteResponse> deleteBook(@Valid @ID @PathVariable(name = "bookId", required = true) @NotNull String bookId){
        BookDeleteRequest request = new BookDeleteRequest(bookId);
        BookDeleteResponse response = bookService.deleteBook(request);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
