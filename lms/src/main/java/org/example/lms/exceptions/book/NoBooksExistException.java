package org.example.lms.exceptions.book;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class NoBooksExistException extends ApplicationException {
    public NoBooksExistException() {
        super(HTTPStatusCodesEnum.NOT_FOUND.getHttpStatusCode(), "No Books Exist", HttpStatus.NOT_FOUND);
    }

    public NoBooksExistException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
