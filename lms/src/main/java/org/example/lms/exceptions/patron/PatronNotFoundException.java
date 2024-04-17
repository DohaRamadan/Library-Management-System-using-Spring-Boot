package org.example.lms.exceptions.patron;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class PatronNotFoundException extends ApplicationException {
    public PatronNotFoundException() {
        super(HTTPStatusCodesEnum.NOT_FOUND.getHttpStatusCode(), "Patron Not Found", HttpStatus.NOT_FOUND);
    }

    public PatronNotFoundException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
