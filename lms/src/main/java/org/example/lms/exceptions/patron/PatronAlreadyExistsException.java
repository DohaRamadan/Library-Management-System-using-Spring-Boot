package org.example.lms.exceptions.patron;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class PatronAlreadyExistsException extends ApplicationException {
    public PatronAlreadyExistsException() {
        super(HTTPStatusCodesEnum.CONFLICT.getHttpStatusCode(), "Patron Already Exists", HttpStatus.CONFLICT);
    }

    public PatronAlreadyExistsException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
