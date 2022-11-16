package com.example.onlinelibrary.web;

import com.example.onlinelibrary.models.dto.AddAuthorDTO;
import com.example.onlinelibrary.models.dto.AddBookDTO;
import com.example.onlinelibrary.service.AuthorService;
import com.example.onlinelibrary.service.BookService;
import com.example.onlinelibrary.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public HomeController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String index(Model model){
        var data = bookService.getAllBooks();
        model.addAttribute("books", data);
        return "index";
    }
    @GetMapping("/authors")
    public String authors(Model model, AddAuthorDTO addAuthorDTO){
        var data = authorService.getAllAuthors();
        model.addAttribute("authors", data);
        model.addAttribute("addAuthor", addAuthorDTO);
        return "authors";
    }

    @PostMapping("/authors/add")
    public String authorsAdd(AddAuthorDTO addAuthorDTO){
        authorService.saveAuthor(addAuthorDTO);
        return "redirect:/authors";
    }

    @GetMapping("/delete/author/{id}")
    public String deleteAuthor(@PathVariable("id") Long authorId){
        authorService.deleteAuthorById(authorId);
        return "redirect:/authors";
    }

    @GetMapping("/genre")
    public String genre(Model model){
        var data = authorService.getAllGenres();
        model.addAttribute("genres", data);
        return "genre";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId){
        bookService.deleteBookById(bookId);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addNewBook(AddBookDTO bookDTO, Model model){
        model.addAttribute("book", bookDTO);
        model.addAttribute("genres", genreService.allGenres());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "newBook";

    }

    @PostMapping("/add")
    public String addNewBookPost(AddBookDTO bookDTO){
        bookService.addNewBook(bookDTO);
        return "redirect:/";

    }
}
