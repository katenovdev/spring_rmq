package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    private MessageProducerService messageProducerService;

    @GetMapping("/all")
    public List<Book> all() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public void add(@RequestBody Book book) {
        messageProducerService.sendNotification(book);
    }
}
