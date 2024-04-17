package org.example.lms.exceptions.borrowingrecord;

public class BookIsNotBorrowedException extends RuntimeException {
    public BookIsNotBorrowedException() {
        super("You have not borrowed this book");
    }

    public BookIsNotBorrowedException(String message) {
        super(message);
    }
}
