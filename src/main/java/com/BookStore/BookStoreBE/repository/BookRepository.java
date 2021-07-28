package com.BookStore.BookStoreBE.repository;

import com.BookStore.BookStoreBE.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    @Override
    List<Book> findAll(Sort sort);

    List <Book> findBooksByTitle (String title);

    Book insert(Book newBook);


    @Override
    void deleteById(String s);

    List<Book> insert(Iterable listOfBooks);

    @Override
    Optional<Book> findById(String s);
}
