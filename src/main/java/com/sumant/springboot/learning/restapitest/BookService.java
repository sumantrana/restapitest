package com.sumant.springboot.learning.restapitest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMybatisRepository bookMybatisRepository;

    public BookService(BookRepository bookRepository, BookMybatisRepository bookMybatisRepository) {
        this.bookRepository = bookRepository;
        this.bookMybatisRepository = bookMybatisRepository;
    }

    public Book getBook(String name) {
        Optional<BookEntity> bookData = bookRepository.findBookDataByName(name);
        if ( bookData.isPresent() ){
            Book book = new Book();
            BeanUtils.copyProperties(bookData.get(), book);
            return book;
        }
        return null;
    }

    /**
     *
     * @param book
     * @return
     * @throws Exception
     *
     * rollbackFor: Defines the Exceptions for which the transaction should be rolled back
     * noRollbackFor: Defines the Exceptions for which the transaction should not be rolled back
     */

    @Transactional( rollbackFor = SQLException.class, noRollbackFor = KafkaPublishException.class )
    public long createBook(Book book) throws Exception{
        long id = createBooks(book);

        try{
            //Method to publish data to Kafka.
            //Please note that we need to throw 2 different types of exceptions
            // 1. SQLExcpetion on which we want to rollback if things are not good.
            // 2. KafkaPublishException on which we still want to commit our transaction.
            // For Kafka publish errors we will use a different retrying mechanism.
            publishData();
        } catch (Exception exp){
            throw new KafkaPublishException("Exception while publishing message to Kafka");
        }


        return id;
    }

    private void publishData(){

    }


    private long createBooks(Book book) throws Exception {

        try {

            BookEntity bookEntity = BookEntity.builder().name(book.getName()).value(book.getValue()).build();
            bookMybatisRepository.insert(bookEntity);

            BookEntity bookEntityDuplicate = BookEntity.builder().value(book.getValue()).build();
            bookMybatisRepository.insert(bookEntityDuplicate);

            return bookEntityDuplicate.getId();

        } catch (Exception exp){
            throw new SQLException("Exception while inserting book data");
        }


    }

}
