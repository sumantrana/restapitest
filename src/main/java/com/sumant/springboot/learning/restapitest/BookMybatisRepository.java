package com.sumant.springboot.learning.restapitest;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMybatisRepository {

    long insert(BookEntity book);

}

