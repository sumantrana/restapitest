package com.sumant.springboot.learning.restapitest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Book Management System", description = "Operations pertaining to book in Book Management System")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;
    }

    @GetMapping(path="/book")
    @ApiOperation(value="Get a book basis name", response = Book.class)
    public Book getBook(@ApiParam(value = "Name of the book to get the details", required = true) @RequestParam(name="name", required = true) String bookName){
        return bookService.getBook(bookName);
    }

    @GetMapping(path = "/defaultBook")
    public Book getDefaultBook(){
        return Book.builder().name("DefaultBook").value(25).build();
    }
}
