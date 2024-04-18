package org.example.lms.exceptions.user;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class UserDoesNotExistException extends ApplicationException {
    public UserDoesNotExistException() {
        super(HTTPStatusCodesEnum.NOT_FOUND.getHttpStatusCode(), "This User Does Not Exist", HttpStatus.NOT_FOUND);
    }

    public UserDoesNotExistException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}