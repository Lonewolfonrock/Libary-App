package com.app.book.book.repo;

import com.app.book.book.model.books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface bookRepo extends JpaRepository<books,Integer>{

    @Query("SELECT b FROM books b WHERE b.bookName LIKE %:bookName%")
    List<books> findAllByBooksName(@Param("bookName") String bookName);

}
