package org.example.lms.exceptions.patron;

public class PatronNotFoundException extends RuntimeException{
    public PatronNotFoundException(String message) {
        super(message);
    }

    public PatronNotFoundException(){
        super("Patron Not Found");
    }
}
