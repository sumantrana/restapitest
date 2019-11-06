package com.sumant.springboot.learning.restapitest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(String name) {
        Optional<BookData> bookData = bookRepository.findBookDataByName(name);
        if ( bookData.isPresent() ){
            Book book = new Book();
            BeanUtils.copyProperties(bookData.get(), book);
            return book;
        }
        return null;
    }

}
