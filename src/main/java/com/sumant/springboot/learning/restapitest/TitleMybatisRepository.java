package com.sumant.springboot.learning.restapitest;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TitleMybatisRepository {

    long insert(TitleEntity titleEntity);

}

