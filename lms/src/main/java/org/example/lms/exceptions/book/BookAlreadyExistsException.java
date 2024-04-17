package org.example.lms.exceptions.book;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class BookAlreadyExistsException extends ApplicationException {

    public BookAlreadyExistsException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }

    public BookAlreadyExistsException() {
        super(HTTPStatusCodesEnum.CONFLICT.getHttpStatusCode(), "This Book Already Exists", HttpStatus.CONFLICT);
    }
}
