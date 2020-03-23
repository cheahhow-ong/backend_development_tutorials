package com.caching.geode_1.gemfire;

import com.caching.geode_1.gemfire.repositories.BookRepository;
import com.caching.geode_1.model.Book;
import com.caching.geode_1.model.BookType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class BooksController {
    private BookRepository bookRepository;

    public BooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/book")
    public void createRandom() {
        bookRepository.save(new Book("book-" + System.currentTimeMillis(),"genre", "author-id",
                "publisher-id", new BookType("book-type-id", "description"),
                ZonedDateTime.now()));
    }

    @GetMapping("/book")
    public Iterable<Book> listBooks() {
        return bookRepository.findAll();
    }

    @DeleteMapping("/book")
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
