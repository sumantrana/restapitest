package com.sumant.springboot.learning.restapitest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping(path="/book")
    public Book getBook(@RequestParam(name="name", required = true) String bookName){
        return bookService.getBook(bookName);
    }
}
