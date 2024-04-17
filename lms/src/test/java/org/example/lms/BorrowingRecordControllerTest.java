package org.example.lms;

import org.example.lms.controllers.BorrowingRecordController;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordBorrowResponse;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordReturnResponse;
import org.example.lms.exceptions.book.BookNotFoundException;
import org.example.lms.exceptions.borrowingrecord.BookIsAlreadyBorrowedException;
import org.example.lms.exceptions.borrowingrecord.BookIsNotBorrowedByPatron;
import org.example.lms.exceptions.borrowingrecord.BookIsNotBorrowedException;
import org.example.lms.exceptions.patron.PatronNotFoundException;
import org.example.lms.services.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBorrowBook_Success() {
        // Prepare
        BorrowingRecordBorrowResponse mockResponse = new BorrowingRecordBorrowResponse();
        when(borrowingRecordService.borrowBook(any(), any())).thenReturn(mockResponse);

        // Execute
        ResponseEntity<BorrowingRecordBorrowResponse> responseEntity = borrowingRecordController.borrowBook("1", "1");

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testBorrowBook_BookNotFound() {
        // Prepare
        when(borrowingRecordService.borrowBook(any(), any())).thenThrow(new BookNotFoundException());

        // Execute and Verify
        assertThrows(BookNotFoundException.class, () -> borrowingRecordController.borrowBook("1", "1"));
    }

    @Test
    public void testBorrowBook_BookAlreadyBorrowed() {
        // Prepare
        when(borrowingRecordService.borrowBook(any(), any())).thenThrow(new BookIsAlreadyBorrowedException());

        // Execute and Verify
        assertThrows(BookIsAlreadyBorrowedException.class, () -> borrowingRecordController.borrowBook("1", "1"));
    }

    @Test
    public void testBorrowBook_PatronNotFound() {
        // Prepare
        when(borrowingRecordService.borrowBook(any(), any())).thenThrow(new PatronNotFoundException());

        // Execute and Verify
        assertThrows(PatronNotFoundException.class, () -> borrowingRecordController.borrowBook("1", "1"));
    }

    @Test
    public void testReturnBook_Success() {
        // Prepare
        BorrowingRecordReturnResponse mockResponse = new BorrowingRecordReturnResponse();
        when(borrowingRecordService.returnBook(any(), any())).thenReturn(mockResponse);

        // Execute
        ResponseEntity<BorrowingRecordReturnResponse> responseEntity = borrowingRecordController.returnBook("1", "1");

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testReturnBook_BookNotFound() {
        // Prepare
        when(borrowingRecordService.returnBook(any(), any())).thenThrow(new BookNotFoundException());

        // Execute and Verify
        assertThrows(BookNotFoundException.class, () -> borrowingRecordController.returnBook("1", "1"));
    }

    @Test
    public void testReturnBook_BookNotBorrowed() {
        // Prepare
        when(borrowingRecordService.returnBook(any(), any())).thenThrow(new BookIsNotBorrowedException());

        // Execute and Verify
        assertThrows(BookIsNotBorrowedException.class, () -> borrowingRecordController.returnBook("1", "1"));
    }

    @Test
    public void testReturnBook_BookNotBorrowedByPatron() {
        // Prepare
        when(borrowingRecordService.returnBook(any(), any())).thenThrow(new BookIsNotBorrowedByPatron());

        // Execute and Verify
        assertThrows(BookIsNotBorrowedByPatron.class, () -> borrowingRecordController.returnBook("1", "1"));
    }
}
