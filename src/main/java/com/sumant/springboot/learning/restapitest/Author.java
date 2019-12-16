package com.sumant.springboot.learning.restapitest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Author {

    int id;
    String name;
    long age;

    List<Title> titleList;
    int book;
}
