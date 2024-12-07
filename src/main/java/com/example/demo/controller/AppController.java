package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class AppController {
    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public List<Book> all() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public List<Book> add(@RequestBody Book book) {
        return bookService.getAllBooks();
    }
}
