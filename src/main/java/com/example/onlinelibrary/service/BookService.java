package com.example.onlinelibrary.service;

import com.example.onlinelibrary.models.Author;
import com.example.onlinelibrary.models.Book;
import com.example.onlinelibrary.models.Genre;
import com.example.onlinelibrary.models.dto.AddBookDTO;
import com.example.onlinelibrary.repository.AuthorRepository;
import com.example.onlinelibrary.repository.BookRepository;
import com.example.onlinelibrary.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks(){
        var listFromDb = bookRepository.findAll();
        return listFromDb;
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }


    public void addNewBook(AddBookDTO bookDTO) {
        Book book = new Book();
        book.setDescription(bookDTO.getDescription());
        book.setTitle(bookDTO.getTitle());
        Genre genreObject = genreRepository.findByName(bookDTO.getGenre()).get();
        book.setGenre(genreObject);
        book.setPictureUrl(bookDTO.getPictureURL());
        book.setAuthor(bookAuthorsMapper(bookDTO.getAuthors()));
        bookRepository.save(book);
    }

    private List<Author> bookAuthorsMapper(String[] authors){
        var authorsFromDB =authorRepository.findAll();
        List<Author> listToReturn = new ArrayList<Author>();

        for (var author: authors){
            for (var authorFromDB: authorsFromDB){
                String authFullName = authorFromDB.getFirstName() + " " + authorFromDB.getLastName();
                if (author.equals(authFullName)){
                    listToReturn.add(authorFromDB);
                    break;
                }
            }
        }
        return listToReturn;
    }

    public void saveElements(List<Book> allBooks) {
        bookRepository.saveAll(allBooks);
    }
}
