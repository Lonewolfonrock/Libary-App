package com.app.book.book.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
public class books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;
    private String bookName;
    private String bookDescription;
    private String bookAuthor;
    private String smallThumbnail;
    private String bigThumbnail;
    private String catagory;

}
