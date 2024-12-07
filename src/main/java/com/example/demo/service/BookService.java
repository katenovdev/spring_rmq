package com.example.demo.service;


import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks() {
//        return books;
        return Arrays.asList(new Book(1, "Title", "Author", "isbn", "Publisher", 2332));
    }


}
