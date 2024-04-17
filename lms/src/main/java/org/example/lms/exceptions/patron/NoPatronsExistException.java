package org.example.lms.exceptions.patron;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class NoPatronsExistException extends ApplicationException {
    public NoPatronsExistException() {
        super(HTTPStatusCodesEnum.NOT_FOUND.getHttpStatusCode(), "No Patrons Exist", HttpStatus.NOT_FOUND);
    }

    public NoPatronsExistException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}

