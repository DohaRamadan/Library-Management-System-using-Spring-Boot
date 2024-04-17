package org.example.lms.exceptions.borrowingrecord;

import org.example.lms.exceptions.ApplicationException;
import org.example.lms.exceptions.HTTPStatusCodesEnum;
import org.springframework.http.HttpStatus;

public class BookIsNotBorrowedByPatron extends ApplicationException {
    public BookIsNotBorrowedByPatron() {
        super(HTTPStatusCodesEnum.CONFLICT.getHttpStatusCode(), "Book Not Borrowed By Pattron", HttpStatus.CONFLICT);
    }

    public BookIsNotBorrowedByPatron(String errorCode, String message, HttpStatus httpStatus) {
        super(errorCode, message, httpStatus);
    }
}
