package com.BookStore.BookStoreBE.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@Document("books")
public class Book {
    @Id
    private String id;
    @TextIndexed
    private String title;
    private int pageCount;
    private String thumbnailUrl;
    private ArrayList<String> authors;
    @TextIndexed
    private String shortDescription;
    private String longDescription;
    private float price;


    public Book(String id, String title, int pageCount, String thumbnailUrl,
                ArrayList<String> authors, String shortDescription, String longDescription, float price) {
        this.id=id;
        this.title = title;
        this.pageCount = pageCount;
        this.thumbnailUrl = thumbnailUrl;
        this.authors = authors;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
    }
}
