package org.example.lms;

import org.example.lms.controllers.BookController;
import org.example.lms.dto.inbound.book.*;
import org.example.lms.dto.outbound.book.*;
import org.example.lms.enums.BookStatusEnum;
import org.example.lms.exceptions.book.*;
import org.example.lms.models.Book;
import org.example.lms.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks_Success() throws Exception {
        // Prepare
        List<Book> books = new ArrayList<>();
        books.add(createSampleBook());
        when(bookService.getAllBooks(any())).thenReturn(new BooksGetResponse(books));

        // Execute
        ResponseEntity<BooksGetResponse> responseEntity = bookController.getAllBooks();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(books.size(), responseEntity.getBody().getBooks().size());
    }

    @Test
    public void testGetBookById_Success() throws Exception {
        // Prepare
        String bookId = "1";
        Book book = createSampleBook();
        when(bookService.getBookById(any())).thenReturn(new BookGetResponse(book));

        // Execute
        ResponseEntity<BookGetResponse> responseEntity = bookController.getBookById(bookId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bookId, responseEntity.getBody().getBook().getId().toString());
    }

    @Test
    public void testAddBook_Success() throws Exception {
        // Prepare
        BookAddRequest addRequest = new BookAddRequest();
        when(bookService.addBook(any())).thenReturn(new BookAddResponse());

        // Execute
        ResponseEntity<BookAddResponse> responseEntity = bookController.addBook(addRequest);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBook_BookNotFound() {
        // Prepare
        String bookId = "1";
        doThrow(BookNotFoundException.class).when(bookService).deleteBook(any());

        // Execute and Verify
        assertThrows(BookNotFoundException.class, () -> bookController.deleteBook(bookId));
    }

    @Test
    public void testUpdateBook_BookNotFound() throws Exception {
        // Prepare
        BookUpdateRequest updateRequest = new BookUpdateRequest();
        doThrow(BookNotFoundException.class).when(bookService).updateBook(any());

        // Execute and Verify
        assertThrows(BookNotFoundException.class, () -> bookController.updateBook(updateRequest));
    }


    @Test
    public void testUpdateBook_Success() throws Exception {
        // Prepare
        BookUpdateRequest updateRequest = new BookUpdateRequest();
        when(bookService.updateBook(any())).thenReturn(new BookUpdateResponse(new BookResponse()));

        // Execute
        ResponseEntity<BookUpdateResponse> responseEntity = bookController.updateBook(updateRequest);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteBook_Success() throws Exception {
        // Prepare
        String bookId = "1";
        when(bookService.deleteBook(any())).thenReturn(new BookDeleteResponse());

        // Execute
        ResponseEntity<BookDeleteResponse> responseEntity = bookController.deleteBook(bookId);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllBooks_NoBooksExist() throws Exception {
        // Prepare
        when(bookService.getAllBooks(any())).thenThrow(new NoBooksExistException());

        // Execute and Verify
        assertThrows(NoBooksExistException.class, () -> bookController.getAllBooks());
    }

    @Test
    public void testGetBookById_BookNotFound() throws Exception {
        // Prepare
        String bookId = "1";
        when(bookService.getBookById(any())).thenThrow(new BookNotFoundException());

        // Execute and Verify
        assertThrows(BookNotFoundException.class, () -> bookController.getBookById(bookId));
    }

    @Test
    public void testAddBook_BookAlreadyExists() throws Exception {
        // Prepare
        BookAddRequest addRequest = new BookAddRequest();
        when(bookService.addBook(any())).thenThrow(new BookAlreadyExistsException());

        // Execute and Verify
        assertThrows(BookAlreadyExistsException.class, () -> bookController.addBook(addRequest));
    }


    // Helper method to create a sample book for testing
    private Book createSampleBook() {
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("John Doe");
        book.setTitle("Sample Book");
        book.setIsbn("978-3-16-148410-0");
        book.setPublicationYear("2022");
        book.setStatus(BookStatusEnum.AVAILABLE);
        return book;
    }
}
