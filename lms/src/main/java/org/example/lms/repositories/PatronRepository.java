package org.example.lms.repositories;

import java.util.Optional;

import org.example.lms.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    @Override
    Optional<Patron> findById(Long aLong);
    Optional<Patron> findByEmail(String email);
}
