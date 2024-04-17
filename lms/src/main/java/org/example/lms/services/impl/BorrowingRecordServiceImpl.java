package org.example.lms.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordBorrowResponse;
import org.example.lms.dto.outbound.borrowingrecord.BorrowingRecordReturnResponse;
import org.example.lms.enums.BookStatusEnum;
import org.example.lms.exceptions.book.BookNotFoundException;
import org.example.lms.exceptions.borrowingrecord.BookIsAlreadyBorrowedException;
import org.example.lms.exceptions.borrowingrecord.BookIsNotBorrowedByPatron;
import org.example.lms.exceptions.borrowingrecord.BookIsNotBorrowedException;
import org.example.lms.exceptions.patron.PatronNotFoundException;
import org.example.lms.models.Book;
import org.example.lms.models.BorrowingRecord;
import org.example.lms.models.Patron;
import org.example.lms.repositories.BookRepository;
import org.example.lms.repositories.BorrowingRecordRepository;
import org.example.lms.repositories.PatronRepository;
import org.example.lms.services.BorrowingRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BorrowingRecordServiceImpl implements BorrowingRecordService {
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    public BorrowingRecordServiceImpl(BookRepository bookRepository, PatronRepository patronRepository,
                                BorrowingRecordRepository borrowingRecordRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }
    @Override
    @Transactional
    public BorrowingRecordBorrowResponse borrowBook(Long bookId, Long patronId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Patron> patronOptional = patronRepository.findById(patronId);

        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        if (patronOptional.isEmpty()) {
            throw new PatronNotFoundException();
        }

        Book book = bookOptional.get();
        Patron patron = patronOptional.get();

        if (book.getStatus().equals(BookStatusEnum.BORROWED)) {
            throw new BookIsAlreadyBorrowedException();
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());

        borrowingRecordRepository.save(borrowingRecord);

        book.setStatus(BookStatusEnum.BORROWED);
        bookRepository.save(book);

        return new BorrowingRecordBorrowResponse();
    }

    @Override
    @Transactional
    public BorrowingRecordReturnResponse returnBook(Long bookId, Long patronId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<Patron> patronOptional = patronRepository.findById(patronId);

        if (bookOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        if (patronOptional.isEmpty()) {
            throw new PatronNotFoundException();
        }

        Book book = bookOptional.get();
        Patron patron = patronOptional.get();

        if (book.getStatus().equals(BookStatusEnum.AVAILABLE)) {
            throw new BookIsNotBorrowedException();
        }

        List<BorrowingRecord> patronBorrowingRecords = borrowingRecordRepository.findBorrowingRecordByBookAndPatronAndReturnDateIsNullOrderByBorrowingDate(book, patron);
        if(patronBorrowingRecords.isEmpty()){
            throw new BookIsNotBorrowedByPatron();
        }
        BorrowingRecord borrowingRecord = patronBorrowingRecords.get(0);
        borrowingRecord.setReturnDate(LocalDate.now());

        borrowingRecordRepository.save(borrowingRecord);

        book.setStatus(BookStatusEnum.AVAILABLE);
        bookRepository.save(book);

        return new BorrowingRecordReturnResponse();
    }
}
