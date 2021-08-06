package com.BookStore.BookStoreBE.service;

import com.BookStore.BookStoreBE.model.Book;
import com.BookStore.BookStoreBE.repository.BookRepository;
import com.BookStore.BookStoreBE.utils.HandleJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    //==========CREATE=======
    public Book insert (String bookDetails) throws JsonProcessingException {
        System.out.println("bookDetails in book service  is");
        System.out.println(bookDetails);
//   Deserialize into Book object
        Book newBook= HandleJson.convertToBookPojo(bookDetails);
        //create an objectId
//        newBook.setId(new ObjectId().toString());
        System.out.println(newBook);
        try {
    //        Insert into db
            Book newBookInstance= bookRepository.insert(newBook);
            System.out.println(newBookInstance.getId());
            System.out.println(bookRepository.findBooksByTitle(newBookInstance.getTitle()));

            System.out.println(newBookInstance);

    //       Validation
//            if (newBookInstance==null) return null;
            return newBookInstance;
        }catch (Exception e){
            System.out.println("There was an error trying to insert the book");
            System.out.println(e);
            return null;
        }
    }

//    Used for seeding only
    public void insertMultiple (List listOfBooks) throws JsonProcessingException {
//   Deserialize into Book object
//        System.out.println("listOfBooks is:");
//        System.out.println(listOfBooks);
//        List <Book> listOfNewBooks= HandleJson.convertToListOfPojos(listOfBooks);

        try{
    //        Insert into db
            List<Book> newBookInstance= bookRepository.insert((Iterable<Book>) listOfBooks);
            //Validation
        }catch (Exception e){
            System.out.println("There was an error inserting multiple books at once");
            System.out.println(e);
        }
    }

    //==========READ=======
    public List <Book> findAll(){
        try {
            List allBooks = bookRepository.findAll();
            return allBooks;
        }catch (Exception e){
            System.out.println("There was an error finding all books:");
            System.out.println(e);
            return null;
        }
    }

    public List<Book> findByTitle(String title){
        try{
        List books= bookRepository.findBooksByTitle(title);
//      Validation if list is empty
        if (books.size()<1) return null;
        return books;
        }catch (Exception e){
            System.out.println("Error trying to find book by title");
            System.out.println(e);
            return null;
        }
    }

    public List<Book> findByText(String text){
        try{
            List books= bookRepository.findAllBy(TextCriteria.forDefaultLanguage().matching(text));
//      Validation if list is empty
            if (books.size()<1) return new ArrayList<Book>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<Book> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(Book book) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends Book> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, Collection<? extends Book> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public Book get(int index) {
                    return null;
                }

                @Override
                public Book set(int index, Book element) {
                    return null;
                }

                @Override
                public void add(int index, Book element) {

                }

                @Override
                public Book remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Book> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<Book> listIterator(int index) {
                    return null;
                }

                @Override
                public List<Book> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };
            return books;
        }catch (Exception e){
            System.out.println("Error trying to find book by text");
            System.out.println(e);
            return null;
        }
    }

    public long count(){
        return bookRepository.count();
    }

//    ==========UPDATE=======
    public Book updateBook(String bookDetails) throws JsonProcessingException {
//        Expect FE to send a json object containing all Book fields, taking note to re-use the
//        original objectid.
//        Parse the object into a book
        Book updatedBookDetails= HandleJson.convertToBookPojo(bookDetails);
        try{
//          Save the updated book to the db
            Book savedBook= bookRepository.save(updatedBookDetails);
//          Optional<Book> book= bookRepository.findById(bookId);
//          Return the book instance
//              Question: Is it best pract to get AllBooks and return it to the FE, or jsut return
//              one book and replace it into the FE state?
//              Chosen approach: tax the BE to query all books, then return all to FE
            return savedBook;
        }catch (Exception e ){
            System.out.println("That was an error updating the book:");
            System.out.println(e);
            return null;
        }
    }

    //==========DELETE=======
    public Boolean deleteBook(String bookId){
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
