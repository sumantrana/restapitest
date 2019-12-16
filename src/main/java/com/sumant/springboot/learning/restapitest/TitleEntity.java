package com.sumant.springboot.learning.restapitest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TitleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    int author;

    public static Title toDomain(TitleEntity titleEntity){

        return Title.builder().id(titleEntity.getId()).name(titleEntity.getName()).author(titleEntity.getAuthor()).build();
    }
}
