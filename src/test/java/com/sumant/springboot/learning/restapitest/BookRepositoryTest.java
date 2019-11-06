package com.sumant.springboot.learning.restapitest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @AfterEach
    public void tearDown(){
        bookRepository.deleteAll();
    }

    @Test
    public void save_WillPersistBook(){
        BookData book = BookData.builder().name("DatabaseBook").value(20).build();
        bookRepository.save(book);

        Optional<BookData> returnedBook = bookRepository.findBookDataByName(book.getName());

        assertThat(returnedBook).isNotEmpty();
        assertThat(returnedBook.get()).isEqualTo(book);
    }
}
