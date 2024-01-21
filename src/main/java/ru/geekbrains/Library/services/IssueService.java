package ru.geekbrains.Library.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.Library.models.Book;
import ru.geekbrains.Library.models.Issue;
import ru.geekbrains.Library.models.IssueRequest;
import ru.geekbrains.Library.repositories.BookRepository;
import ru.geekbrains.Library.repositories.IssueReposirory;
import ru.geekbrains.Library.repositories.ReaderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueReposirory issueReposirory;

    public Issue addIssue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId(), LocalDate.now());
        List<Issue> issues = issueReposirory.getAllIssue();
        for (int i = 0; i < issues.size(); i++) {
            if(request.getBookId() == issues.get(i).getBookId() && issues.get(i).getReturned_at() == null){
                return null;
            }
        }
        issueReposirory.addIssue(issue);
        return issue;
    }
    public List<Book> getBooksByReader(long id){
        List<Issue> issues =  issueReposirory.getAllIssue().stream()
                .filter(it -> it.getReaderId() == id && it.getReturned_at() == null)
                .toList();
        List<Book> books = new ArrayList<>();
        for (Issue issue : issues){
            books.add(bookRepository.getBookById(issue.getBookId()));
        }
        return books;
    }

}
