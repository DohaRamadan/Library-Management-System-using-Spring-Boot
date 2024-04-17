package org.example.lms.exceptions.book;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class BookNotFoundException extends ApplicationException {
    public BookNotFoundException() {
        super(HTTPStatusCodesEnum.NOT_FOUND.getHttpStatusCode(), "Book not found", HttpStatus.NOT_FOUND);
    }

    public BookNotFoundException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
