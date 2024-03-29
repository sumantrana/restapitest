package com.sumant.springboot.learning.restapitest;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {

    private int id;
    private String name;
    private long value;

    List<Author> authorList;
}
