package com.sumant.springboot.learning.restapitest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    long age;

    List<TitleEntity> titleList;
    int book;

    public static Author toDomain(AuthorEntity authorEntity){

        Author author = Author.builder().id(authorEntity.getId()).name(authorEntity.getName()).age(authorEntity.getAge()).book(authorEntity.getBook()).build();

        if ( authorEntity.getTitleList() != null && authorEntity.getTitleList().size() > 0 ){

            author.setTitleList(new ArrayList<Title>());

            for(TitleEntity titleEntity : authorEntity.getTitleList() ){

                author.getTitleList().add(TitleEntity.toDomain(titleEntity));
            }
        }

        return author;
    }
}
