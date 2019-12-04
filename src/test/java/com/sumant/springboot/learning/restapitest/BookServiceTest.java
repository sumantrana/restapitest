package com.sumant.springboot.learning.restapitest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {

    private static BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeAll
    public void setup(){
        //initMocks(this);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void getBook_WillReturn_HardcodedBook(){

        BookData bookData = BookData.builder().name("TestDBName").value(25).build();
        given(bookRepository.findBookDataByName("TestDBName")).willReturn(Optional.of(bookData));

        Book book = bookService.getBook(bookData.getName());
        assertThat(book.getName()).isEqualTo("TestDBName");

        verify(bookRepository).findBookDataByName("TestDBName");

    }
}
