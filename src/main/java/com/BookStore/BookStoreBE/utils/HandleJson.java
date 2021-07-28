package com.BookStore.BookStoreBE.utils;

import com.BookStore.BookStoreBE.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleJson {

    public static String deserializeToString(String jsonObj, String key ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef= new TypeReference<HashMap<String, String>>() {
        };
        Map<String, String> map= mapper.readValue(jsonObj, typeRef );
        return map.get(key);
    }

    public static Book convertToBookPojo(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();
        return mapper.readValue(jsonString, Book.class);
    }

    public static List convertToListOfPojos(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();

        return mapper.readValue(jsonString, List.class);

    }
}
