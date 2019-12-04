package com.sumant.springboot.learning.restapitest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class BookControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;


    @BeforeEach
    public void setup(){
        //initMocks(this);
        BookController bookController = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getBook_WillReturn_Book() throws Exception {

        Book book = Book.builder().name("TestName").value(10).build();
        given(bookService.getBook("TestName")).willReturn(book);

        mockMvc.perform(get("/book").param("name", "TestName"))
                .andExpect(jsonPath("$.name", is("TestName")))
                .andExpect(jsonPath("$.value", is(10)));

        verify(bookService, times(1)).getBook(anyString());

    }
}
