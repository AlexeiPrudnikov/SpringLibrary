package ru.geekbrains.Library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.Library.models.Book;
import ru.geekbrains.Library.models.Issue;
import ru.geekbrains.Library.models.Reader;
import ru.geekbrains.Library.repositories.BookRepository;
import ru.geekbrains.Library.repositories.IssueReposirory;
import ru.geekbrains.Library.repositories.ReaderRepository;
import ru.geekbrains.Library.services.IssueService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/ui")
public class UIHtmlController {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueReposirory issueReposirory;
    private final IssueService issueService;

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookRepository.getAllBooks();
        model.addAttribute("books", books);
        return "books";
        }
    @GetMapping("/readers")
    public String getAllReaders (Model model){
        List<Reader> readers = readerRepository.getAllReaders();
        model.addAttribute("readers", readers);
        return "readers";
    }
    @GetMapping("/issues")
    public String getAllIssues(Model model){
        List<Issue> issues = issueReposirory.getAllIssue();
        model.addAttribute("issues", issues);
        model.addAttribute("readerRepository", readerRepository);
        model.addAttribute("bookRepository", bookRepository);
        return "issues";
    }
    @GetMapping("/readers/{id}")
    public String getBooksByReader (Model model, @PathVariable long id){
        Reader reader = readerRepository.getReaderById(id);
        List<Book> books = issueService.getBooksByReader(id);
        model.addAttribute("reader", reader);
        model.addAttribute("books", books);
        return "booksbyreader";

    }


}
