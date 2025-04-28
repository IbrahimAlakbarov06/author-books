package turing.edu.az.authorbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turing.edu.az.authorbooks.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}