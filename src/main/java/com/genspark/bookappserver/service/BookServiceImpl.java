package com.genspark.bookappserver.service;

import com.genspark.bookappserver.dao.BookDao;
import com.genspark.bookappserver.exception.BookNotFoundException;
import com.genspark.bookappserver.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Collection<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        if(isbn == null || isbn.isBlank()) {
            return Optional.empty();
        }
        return bookDao.findById(isbn);
    }

    @Override
    public void addBook(Book book) {
        if(book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        bookDao.save(book);
    }

    @Override
    public void updateBook(Book book) {
        if(book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if(getBookByIsbn(book.getIsbn()).isEmpty()) {
            throw new BookNotFoundException("Attempting to update non-existent book.");
        }
        bookDao.save(book);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        if(getBookByIsbn(isbn).isEmpty()) {
            throw new BookNotFoundException("Attempting to delete non-existent book.");
        }
        bookDao.deleteById(isbn);
    }
}
