package org.example.lms;

import org.example.lms.controllers.BookController;
import org.example.lms.dto.inbound.book.*;
import org.example.lms.dto.outbound.book.*;
import org.example.lms.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        BooksGetResponse expectedResponse = new BooksGetResponse(Collections.emptyList());
        when(bookService.getAllBooks(any())).thenReturn(expectedResponse);

        ResponseEntity<BooksGetResponse> responseEntity = bookController.getAllBooks();

        verify(bookService).getAllBooks(any());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testGetBookById() throws Exception {
        BookGetResponse expectedResponse = new BookGetResponse();
        when(bookService.getBookById(any())).thenReturn(expectedResponse);

        ResponseEntity<BookGetResponse> responseEntity = bookController.getBookById("1");

        verify(bookService).getBookById(any());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testAddBook() throws Exception {
        BookAddRequest request = new BookAddRequest();
        BookAddResponse expectedResponse = new BookAddResponse();
        when(bookService.addBook(any())).thenReturn(expectedResponse);

        ResponseEntity<BookAddResponse> responseEntity = bookController.addBook(request);

        verify(bookService).addBook(any());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookUpdateRequest request = new BookUpdateRequest();
        BookUpdateResponse expectedResponse = new BookUpdateResponse();
        when(bookService.updateBook(any())).thenReturn(expectedResponse);

        ResponseEntity<BookUpdateResponse> responseEntity = bookController.updateBook(request);

        verify(bookService).updateBook(any());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testDeleteBook() {
        BookDeleteResponse expectedResponse = new BookDeleteResponse();
        when(bookService.deleteBook(any())).thenReturn(expectedResponse);

        ResponseEntity<BookDeleteResponse> responseEntity = bookController.deleteBook("1");

        verify(bookService).deleteBook(any());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
