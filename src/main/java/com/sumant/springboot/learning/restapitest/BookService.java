package com.sumant.springboot.learning.restapitest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class BookService {

    //private BookRepository bookRepository;
    private BookMybatisRepository bookMybatisRepository;
    private AuthorMybatisRepository authorMybatisRepository;
    private TitleMybatisRepository titleMybatisRepository;


    public BookService(BookMybatisRepository bookMybatisRepository,
                       AuthorMybatisRepository authorMybatisRepository,
                       TitleMybatisRepository titleMybatisRepository
    ) {
        //this.bookRepository = bookRepository;
        this.bookMybatisRepository = bookMybatisRepository;
        this.authorMybatisRepository = authorMybatisRepository;
        this.titleMybatisRepository = titleMybatisRepository;
    }

//    public Book getBook(String name) {
//        Optional<BookEntity> bookData = bookRepository.findBookDataByName(name);
//        if ( bookData.isPresent() ){
//            Book book = new Book();
//            BeanUtils.copyProperties(bookData.get(), book);
//            return book;
//        }
//        return null;
//    }

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

    public Book findBookById(int id){

        BookEntity bookEntity = bookMybatisRepository.findById(id);

        System.out.println(bookEntity);

        return BookEntity.toDomain(bookEntity);
    }


    private long createBooks(Book book) throws Exception {

        try {

            BookEntity bookEntity = BookEntity.builder().name(book.getName()).value(book.getValue()).build();
            bookMybatisRepository.insert(bookEntity);

            AuthorEntity authorEntity = AuthorEntity.builder().name("Sumant").age(40).book(bookEntity.getId()).build();
            authorMybatisRepository.insert(authorEntity);

            TitleEntity titleEntity = TitleEntity.builder().name("SumantTitle").author(authorEntity.getId()).build();
            titleMybatisRepository.insert(titleEntity);


            return bookEntity.getId();

        } catch (Exception exp){
            exp.printStackTrace();
            throw new SQLException("Exception while inserting book data");
        }


    }

}
