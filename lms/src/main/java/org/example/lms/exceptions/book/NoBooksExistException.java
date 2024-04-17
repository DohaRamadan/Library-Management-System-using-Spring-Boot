package org.example.lms.exceptions.book;

public class NoBooksExistException extends RuntimeException {
    public NoBooksExistException(){
        super("No Books Exist");
    }
    public NoBooksExistException(String message) {
        super(message);
    }
}
