package org.example.lms.exceptions;



import org.example.lms.exceptions.book.BookAlreadyExistsException;
import org.example.lms.exceptions.book.BookNotFoundException;
import org.example.lms.exceptions.book.NoBooksExistException;
import org.example.lms.exceptions.borrowingrecord.BookIsAlreadyBorrowedException;
import org.example.lms.exceptions.borrowingrecord.BookIsNotBorrowedByPatron;
import org.example.lms.exceptions.borrowingrecord.BookIsNotBorrowedException;
import org.example.lms.exceptions.patron.NoPatronsExistException;
import org.example.lms.exceptions.patron.PatronAlreadyExistsException;
import org.example.lms.exceptions.patron.PatronNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookAlreadyExistsException.class, PatronAlreadyExistsException.class, BookIsAlreadyBorrowedException.class})
    public ResponseEntity<String> handleAlreadyExistsException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({BookNotFoundException.class, PatronNotFoundException.class, BookIsNotBorrowedException.class, BookIsNotBorrowedByPatron.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({NoBooksExistException.class, NoPatronsExistException.class})
    public ResponseEntity<String> handleNoExistenceException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
    }
}