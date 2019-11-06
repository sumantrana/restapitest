package com.sumant.springboot.learning.restapitest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookData, Integer> {

    Optional<BookData> findBookDataByName( String name );
}
