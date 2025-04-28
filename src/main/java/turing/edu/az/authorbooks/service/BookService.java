package turing.edu.az.authorbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turing.edu.az.authorbooks.model.Book;
import turing.edu.az.authorbooks.repository.AuthorRepository;
import turing.edu.az.authorbooks.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Optional<Book> createBook(Book book) {
        return authorRepository.findById(book.getAuthor().getId())
                .map(author -> {
                    book.setAuthor(author);
                    return bookRepository.save(book);
                });
    }

    public Optional<Book> updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setIsbn(bookDetails.getIsbn());

                    if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
                        authorRepository.findById(bookDetails.getAuthor().getId())
                                .ifPresent(existingBook::setAuthor);
                    }

                    return bookRepository.save(existingBook);
                });
    }

    public boolean deleteBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return true;
                })
                .orElse(false);
    }
}