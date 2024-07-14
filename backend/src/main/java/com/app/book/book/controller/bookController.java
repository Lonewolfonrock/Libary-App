package com.app.book.book.controller;

import com.app.book.book.model.books;
import com.app.book.book.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class bookController {

    @Autowired
    bookService bookService;

    @GetMapping("/books")
    public  ResponseEntity<List<books>> gellAll(){
        return new ResponseEntity<>(bookService.getAllBook(),HttpStatus.OK);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<books> getByID(@PathVariable int id){

        books bookD = bookService.getBookbyId(id);
        if (bookD!=null){
            return new ResponseEntity<>(bookD,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

    @PostMapping("/add")
    public ResponseEntity<books> addBooks(@RequestBody books books) {
        try {
            books books1 = bookService.addBook(books);
            return new ResponseEntity<>(books1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}
