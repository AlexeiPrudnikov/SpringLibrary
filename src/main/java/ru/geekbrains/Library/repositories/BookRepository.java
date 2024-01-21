package ru.geekbrains.Library.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Library.models.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<Book> books;

    public BookRepository() {
        books = new ArrayList<>();
    }
    @PostConstruct
    public void generateData(){
        books.addAll(List.of(
                new Book("Золотой теленок"),
                new Book("Мертвые души"),
                new Book("Ревизор"),
                new Book("Козленок в молоке")
        ));
    }
    public Book getBookById(Long id){
        return books.stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Book> getAllBooks(){
        return List.copyOf(books);
    }
    public Book addBook(String bookName){
        Book newBook = new Book(bookName);
        books.add(newBook);
        return newBook;
    }
    public boolean deleteBook(long id){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}
