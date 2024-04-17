package org.example.lms.exceptions.borrowingrecord;

public class BookIsNotBorrowedByPatron extends RuntimeException{
    public BookIsNotBorrowedByPatron() {
        super("You have not borrowed this book");
    }

    public BookIsNotBorrowedByPatron(String message) {
        super(message);
    }
}
