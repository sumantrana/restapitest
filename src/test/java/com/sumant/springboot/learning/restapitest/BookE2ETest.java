package com.sumant.springboot.learning.restapitest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private BookRepository bookRepository;

    @BeforeAll
    public void setup(){
        BookData bookData = BookData.builder().name("E2EBook").value(50).build();
        bookRepository.saveAndFlush(bookData);
    }

    @Test
    public void getBook_WillReturn_SavedBook(){

        Optional<BookData> bookData = bookRepository.findBookDataByName("E2EBook");
        assertThat(bookData.isPresent());

        Book book = testRestTemplate.getForObject("http://localhost:" + port + "/book?name=E2EBook", Book.class);
        assertThat(book.getName()).isEqualTo("E2EBook");

    }
}
