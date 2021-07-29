package com.BookStore.BookStoreBE.seeders;
import com.BookStore.BookStoreBE.model.Book;
import com.BookStore.BookStoreBE.service.BookService;
import com.BookStore.BookStoreBE.utils.HandleJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class index implements CommandLineRunner {
    @Autowired
    BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        //  Prevent double-seeding of data
        if (bookService.count()>0) return;
        //Else the db is empty; seed the data inside
        try {
        ObjectMapper mapper = new ObjectMapper();
        //Get the Json array from the books.json folder, then save it as a list
        List<Book> books = Arrays.asList(mapper.readValue(Paths.get("src/main/java/com/BookStore" +
                        "/BookStoreBE/seeders/books.json").toFile(),
                Book[].class));

            bookService.insertMultiple( books);
        //Insert the list into mongodb
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}