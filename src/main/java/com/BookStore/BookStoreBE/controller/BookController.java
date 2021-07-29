package com.BookStore.BookStoreBE.controller;

import com.BookStore.BookStoreBE.model.Book;
import com.BookStore.BookStoreBE.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;


    //==========CREATE=======
    @PostMapping("books/insert")
    public ResponseEntity<String> insert(@RequestBody String newBook) throws JsonProcessingException {

        Book insertedBook= bookService.insert(newBook);
        if (insertedBook==null){
            return ResponseEntity.status(500).body("Unable to insert book");
        }
        //Stringify and send it back to Fe
        return ResponseEntity.status(200).body(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(insertedBook));

    }

    //==========READ=======
    @RequestMapping("books/findAll")
    public ResponseEntity<String> findAll() throws JsonProcessingException {
        List allBooks= bookService.findAll();
        //If allBooks is an empty list, then no books were found. Send a 500 error
        if  (allBooks.size()<1){
            return ResponseEntity.status(500).body("No books found");
        }
        //Stringify and send it back to Fe
        return ResponseEntity.status(200).body(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(allBooks));
    }

    @RequestMapping("books/findByTitle")
    public ResponseEntity <String> findByTitle(@RequestParam String title) throws JsonProcessingException {
        List <Book> allBooks= bookService.findByTitle(title);
        //If allBooks is an empty list, then no books were found. Send a 500 error
        if  (allBooks==null){
            return ResponseEntity.status(500).body("No books with the specified title found");
        }
        //Else allBooks list is not empty, JSON stringify the list and return it to FE
        return ResponseEntity.status(200).body(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(allBooks));
    }

    //    ==========UPDATE=======
    @PutMapping("/books/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody String newDetails) throws JsonProcessingException {
        //Update the book in the DB
        bookService.updateBook(newDetails);
        //Get a list of all the books
        return this.findAll();
    }

    //==========DELETE=======
    @DeleteMapping("books/deleteBook")
    public ResponseEntity<String> deleteById(@RequestParam String bookId) throws JsonProcessingException {
        //Delete the book in the DB
        bookService.deleteBook(bookId);
        //Return a list of all the books
        return this.findAll();
    }

}
