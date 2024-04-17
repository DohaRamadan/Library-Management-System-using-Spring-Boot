package org.example.lms.controllers;

import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordBorrowResponse;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordReturnResponse;
import org.example.lms.services.BorrowingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordBorrowResponse> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordBorrowResponse response = borrowingRecordService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordReturnResponse> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecordReturnResponse response = borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(response);
    }
}

