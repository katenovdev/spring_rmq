package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.MessageProducerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final MessageProducerService messageProducerService;

    public BookController(BookService bookService, MessageProducerService messageProducerService) {
        this.bookService = bookService;
        this.messageProducerService = messageProducerService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        messageProducerService.sendNotification(book);
        return bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book existing = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found!"));
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setPublisher(updatedBook.getPublisher());
        existing.setPages(updatedBook.getPages());
        return bookService.saveBook(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }
}
