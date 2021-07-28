package com.BookStore.BookStoreBE.service;

import com.BookStore.BookStoreBE.model.Book;
import com.BookStore.BookStoreBE.repository.BookRepository;
import com.BookStore.BookStoreBE.utils.HandleJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    //==========CREATE=======
    public Book insert (String bookDetails) throws JsonProcessingException {
//   Deserialize into Book object
        Book newBook= HandleJson.convertToBookPojo(bookDetails);
//        Insert into db
        Book newBookInstance= bookRepository.insert(newBook);
//       Validation
        if (newBookInstance==null) return null;
        return newBookInstance;
    }

//    Used for seeding only
    public void insertMultiple (List listOfBooks) throws JsonProcessingException {
//   Deserialize into Book object
        System.out.println("listOfBooks is:");
        System.out.println(listOfBooks);
//        List <Book> listOfNewBooks= HandleJson.convertToListOfPojos(listOfBooks);



//        Insert into db
        List<Book> newBookInstance= bookRepository.insert((Iterable<Book>) listOfBooks);


//       Validation
//        if (newBookInstance==null) return null;
//        return newBookInstance;
    }

    //==========READ=======
    public List <Book> findAll(){
        List allBooks= bookRepository.findAll();
        //      Validation if list is empty
        if (allBooks.size()<1) return null;

        return allBooks;
    }

    public List<Book> findByTitle(String title){
        List books= bookRepository.findBooksByTitle(title);
//      Validation if list is empty
        if (books.size()<1) return null;
        return books;
    }

    //==========UPDATE=======
//    public Book updateBook(String bookId){
////        Use id to find the book
//        try{
//            Optional<Book> book= bookRepository.findById(bookId);
//
//        }catch (Exception e ){
//            System.out.println("That was an error updating the book:");
//            System.out.println(e);
//            return null;
//        }
//
//    }


    //==========DELETE=======

    public Boolean deleteById(String bookId){
    try {
//       Delete db record using the book's id
        bookRepository.deleteById(bookId);
        return true;
    }catch (Exception e) {
        System.out.println("Delete failed");
        System.out.println(e);
        return false;
    }
    }


}
