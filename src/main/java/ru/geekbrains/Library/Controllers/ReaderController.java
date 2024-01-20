package ru.geekbrains.Library.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Library.Models.Book;
import ru.geekbrains.Library.Models.Issue;
import ru.geekbrains.Library.Models.Reader;
import ru.geekbrains.Library.Repositories.ReaderRepository;
import ru.geekbrains.Library.Services.ReaderService;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderRepository readerRepository;
    private final ReaderService readerService;
    public ReaderController(ReaderRepository readerRepository, ReaderService readerService) {
        this.readerRepository = readerRepository;
        this.readerService = readerService;
    }
    @GetMapping("/all")
    public List<Reader> getAllReaders(){
        return readerRepository.getAllReaders();
    }
    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id){
        return readerRepository.getReaderById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable long id){
        if (!readerRepository.deleteReader(id)){
            throw new NoSuchElementException("Не найдена книга с идентификатором " + id);
        }
    }
    @PutMapping
    public Reader addReader(@RequestParam String name){
        return readerRepository.addReader(name);
    }
    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssue(@PathVariable long id){
        return readerService.getReaderIssue(id);
    }


}
