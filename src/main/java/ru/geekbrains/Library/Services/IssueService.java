package ru.geekbrains.Library.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.Library.Models.Issue;
import ru.geekbrains.Library.Models.IssueRequest;
import ru.geekbrains.Library.Repositories.BookRepository;
import ru.geekbrains.Library.Repositories.IssueReposirory;
import ru.geekbrains.Library.Repositories.ReaderRepository;

import java.time.LocalDate;
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

}
