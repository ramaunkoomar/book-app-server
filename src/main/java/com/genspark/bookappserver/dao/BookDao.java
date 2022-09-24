package com.genspark.bookappserver.dao;

import com.genspark.bookappserver.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, String> {
}
