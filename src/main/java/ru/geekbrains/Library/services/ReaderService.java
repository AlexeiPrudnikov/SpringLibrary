package ru.geekbrains.Library.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.Library.models.Issue;
import ru.geekbrains.Library.repositories.BookRepository;
import ru.geekbrains.Library.repositories.IssueReposirory;
import ru.geekbrains.Library.repositories.ReaderRepository;

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
