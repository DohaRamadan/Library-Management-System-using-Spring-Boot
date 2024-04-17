package org.example.lms.exceptions.patron;

public class PatronAlreadyExistsException extends RuntimeException{
    public PatronAlreadyExistsException(String message) {
        super(message);
    }

    public PatronAlreadyExistsException() {
        super("Patron Already Exists");
    }
}
