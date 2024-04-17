package org.example.lms.exceptions.patron;

public class NoPatronsExistException extends RuntimeException{
    public NoPatronsExistException(String message) {
        super(message);
    }

    public NoPatronsExistException() {
        super("No Patrons Exist");
    }
}
