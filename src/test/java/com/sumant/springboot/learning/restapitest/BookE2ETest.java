package com.sumant.springboot.learning.restapitest;

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
        BookJpaEntity bookData = BookJpaEntity.builder().name("E2EBook").value(50).build();
        bookRepository.saveAndFlush(bookData);
    }

    @Test
    public void getBook_WillReturn_SavedBook(){

        Optional<BookJpaEntity> bookData = bookRepository.findBookDataByName("E2EBook");
        assertThat(bookData.isPresent());

        Book book = testRestTemplate.getForObject("http://localhost:" + port + "/book?name=E2EBook", Book.class);
        assertThat(book.getName()).isEqualTo("E2EBook");

    }

    @Test
    public void createBook_WillCreateBook_AndReturn_BookId(){

        Book book = Book.builder().name("E2ETestBook").value(1).build();

        long id = testRestTemplate.postForObject("http://localhost:" + port + "/books", book, Long.class);
        //assertThat(bookResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(id).isEqualTo(2);
    }
}
