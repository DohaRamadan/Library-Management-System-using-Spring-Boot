package org.example.lms.exceptions.borrowingrecord;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class BookIsNotBorrowedException extends ApplicationException {
    public BookIsNotBorrowedException() {
        super(HTTPStatusCodesEnum.NOT_FOUND.getHttpStatusCode(), "Book Is Not Borrowed", HttpStatus.NOT_FOUND);
    }

    public BookIsNotBorrowedException(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
