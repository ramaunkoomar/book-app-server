package com.genspark.bookappserver.controller;

import com.genspark.bookappserver.exception.BookNotFoundException;
import com.genspark.bookappserver.model.Book;
import com.genspark.bookappserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("{isbn}")
    public ResponseEntity<Book> getBookById(@PathVariable("isbn") String isbn) {
        Optional<Book> book = bookService.getBookByIsbn(isbn);
        return book
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Book> deleteBookById(@PathVariable("isbn") String isbn) {
        try {
            bookService.deleteBookByIsbn(isbn);
            return ResponseEntity.noContent().build();
        }
        catch(BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            return ResponseEntity.noContent().build();
        }
        catch(BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooks() {
        try {
            return ResponseEntity.ok(bookService.getAllBooks());
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
