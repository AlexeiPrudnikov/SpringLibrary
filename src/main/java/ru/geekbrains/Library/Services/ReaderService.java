package ru.geekbrains.Library.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.Library.Models.Issue;
import ru.geekbrains.Library.Repositories.BookRepository;
import ru.geekbrains.Library.Repositories.IssueReposirory;
import ru.geekbrains.Library.Repositories.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueReposirory issueReposirory;
    public List<Issue> getReaderIssue(long userId){
        return issueReposirory.getAllIssue().stream()
                .filter(it -> it.getReaderId() == userId)
                .toList();
    }
}
