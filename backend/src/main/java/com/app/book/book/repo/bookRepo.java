package com.app.book.book.repo;

import com.app.book.book.model.books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepo extends JpaRepository<books,Integer>{}
