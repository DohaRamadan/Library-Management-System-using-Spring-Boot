package org.example.lms.exceptions.borrowingrecord;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class BookIsAlreadyBorrowedException extends ApplicationException {
    public BookIsAlreadyBorrowedException() {
        super(HTTPStatusCodesEnum.CONFLICT.getHttpStatusCode(), "Book Is Already Borrowed", HttpStatus.CONFLICT);
    }

    public BookIsAlreadyBorrowedException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
