package com.sumant.springboot.learning.restapitest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private long value;

    List<AuthorEntity> authorList;

    public static Book toDomain(BookEntity bookEntity){

        Book book = Book.builder().id(bookEntity.getId()).name(bookEntity.getName()).value(bookEntity.getValue()).build();

        if ( bookEntity.getAuthorList() != null && bookEntity.getAuthorList().size() > 0 ) {

            book.setAuthorList(new ArrayList<Author>());
            for (AuthorEntity authorEntity : bookEntity.getAuthorList()) {

                book.getAuthorList().add(AuthorEntity.toDomain(authorEntity));



            }
        }

        return book;

    }
}
