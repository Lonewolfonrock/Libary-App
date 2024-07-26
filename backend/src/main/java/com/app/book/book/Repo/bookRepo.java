package com.app.book.book.Repo;

import com.app.book.book.Entity.books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface bookRepo extends JpaRepository<books,Integer>{

    @Query("SELECT b FROM books b WHERE b.bookName LIKE :bookName%")
    List<books> findAllByBooksName(@Param("bookName") String bookName);

}
