package org.example.lms.exceptions.borrowingrecord;

public class BookIsAlreadyBorrowedException extends RuntimeException {
    public BookIsAlreadyBorrowedException() {
        super("Book Is Already Borrowed");
    }

    public BookIsAlreadyBorrowedException(String message) {
        super(message);
    }
}
