
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turing.edu.az.authorbooks.model.Author;
import turing.edu.az.authorbooks.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> updateAuthor(Long id, Author authorDetails) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(authorDetails.getName());
                    existingAuthor.setEmail(authorDetails.getEmail());
                    return authorRepository.save(existingAuthor);
                });
    }

    public boolean deleteAuthor(Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    authorRepository.delete(author);
                    return true;
                })
                .orElse(false);
    }
}