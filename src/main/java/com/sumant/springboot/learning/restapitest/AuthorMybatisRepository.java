package com.sumant.springboot.learning.restapitest;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMybatisRepository {

    long insert(AuthorEntity authorEntity);

    List<AuthorEntity> findByBook(int id);

}

