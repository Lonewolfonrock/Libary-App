package com.app.book.book.Service;


import com.app.book.book.Entity.books;
import com.app.book.book.Repo.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookService {

    @Autowired
    bookRepo bookRepo;


    public List<books> getAllBook() {
        return bookRepo.findAll();
    }

    public books getBookbyId(int id) {
        return bookRepo.findById(id).orElse(new books());
    }

    public books addBook(books books) {
        return bookRepo.save(books);
    }

    public List<books> findAllByBookNameStartingWith(String bookName) {
        return bookRepo.findAllByBooksName(bookName);
    }


}
