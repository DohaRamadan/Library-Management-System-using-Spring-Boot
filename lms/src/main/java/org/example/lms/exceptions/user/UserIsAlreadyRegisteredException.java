package org.example.lms.exceptions.user;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class UserIsAlreadyRegisteredException extends ApplicationException {
    public UserIsAlreadyRegisteredException() {
        super(HTTPStatusCodesEnum.CONFLICT.getHttpStatusCode(), "This User Is Already Registered", HttpStatus.CONFLICT);
    }

    public UserIsAlreadyRegisteredException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
