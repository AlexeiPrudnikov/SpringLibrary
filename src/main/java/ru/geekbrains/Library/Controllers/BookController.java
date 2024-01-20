package ru.geekbrains.Library.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Library.Models.Book;
import ru.geekbrains.Library.Repositories.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id){
        return bookRepository.getBookById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        if (!bookRepository.deleteBook(id)){
            throw new NoSuchElementException("Не найдена книга с идентификатором " + id);
        }
    }
    @PutMapping
    public Book addBook(@RequestParam String name){
        return bookRepository.addBook(name);
    }

}
