package org.example.lms.repositories;

import java.util.Optional;

import org.example.lms.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findBookById(Long Id);
}
