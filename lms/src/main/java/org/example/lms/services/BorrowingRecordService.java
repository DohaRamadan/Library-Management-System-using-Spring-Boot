package org.example.lms.services;

import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordBorrowResponse;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordReturnResponse;

public interface BorrowingRecordService {
    BorrowingRecordBorrowResponse borrowBook(Long bookId, Long patronId);
    BorrowingRecordReturnResponse returnBook(Long bookId, Long patronId);
}
