package com.genspark.bookappserver.service;

import com.genspark.bookappserver.model.Book;
import java.util.Collection;
import java.util.Optional;

public interface BookService {
    Collection<Book> getAllBooks();
    Optional<Book> getBookByIsbn(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBookByIsbn(String isbn);
}
