package com.example.onlinelibrary.service;

import com.example.onlinelibrary.models.Author;
import com.example.onlinelibrary.models.Genre;
import com.example.onlinelibrary.models.dto.AddAuthorDTO;
import com.example.onlinelibrary.repository.AuthorRepository;
import com.example.onlinelibrary.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookService bookService;

    public AuthorService(AuthorRepository authorRepository, GenreRepository genreRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookService = bookService;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public void saveAuthor(AddAuthorDTO addAuthorDTO) {
        Author author = new Author();
        author.setFirstName(addAuthorDTO.getFirstName());
        author.setLastName(addAuthorDTO.getLastName());
        author.setAge(addAuthorDTO.getAge());
        author.setNationality(addAuthorDTO.getNationality());

        authorRepository.save(author);
    }

    public void deleteAuthorById(Long authorId) {
        var allBooks = bookService.getAllBooks().stream().filter(x -> x.getAuthor().stream().anyMatch(y -> y.getId() == authorId)).collect(Collectors.toList());
        var author = authorRepository.findById(authorId).get();

        for (var book : allBooks) {
            book.getAuthor().remove(author);
        }

        bookService.saveElements(allBooks);
        authorRepository.deleteById(authorId);
    }
}
