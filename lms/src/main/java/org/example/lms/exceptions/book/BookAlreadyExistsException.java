package org.example.lms.exceptions.book;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(){
        super("Book Already found");
    }
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
