package org.example.lms.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.lms.models.Book;
import org.example.lms.models.BorrowingRecord;
import org.example.lms.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findBorrowingRecordByBookAndPatronAndReturnDateIsNullOrderByBorrowingDate(Book book, Patron patron);

}
