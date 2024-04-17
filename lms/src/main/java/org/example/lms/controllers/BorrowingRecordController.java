package org.example.lms.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordBorrowResponse;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordReturnResponse;
import org.example.lms.services.BorrowingRecordService;
import org.example.lms.validtors.ID;
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
    public ResponseEntity<BorrowingRecordBorrowResponse> borrowBook(@Valid @ID @PathVariable(name = "bookId", required = true) @NotNull String bookId, @Valid @ID @PathVariable(name = "patronId", required = true) @NotNull String patronId) {
        BorrowingRecordBorrowResponse response = borrowingRecordService.borrowBook(Long.valueOf(bookId), Long.valueOf(patronId));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecordReturnResponse> returnBook(@Valid @ID @PathVariable(name = "bookId", required = true) @NotNull String bookId, @Valid @ID @PathVariable(name = "patronId", required = true) @NotNull String patronId) {
        BorrowingRecordReturnResponse response = borrowingRecordService.returnBook(Long.valueOf(bookId), Long.valueOf(patronId));
        return ResponseEntity.ok(response);
    }
}

