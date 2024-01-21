package ru.geekbrains.Library.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Library.models.Issue;

import java.time.LocalDate;
import java.util.List;

@Repository
public class IssueReposirory {
    private final List<Issue> issues;

    public IssueReposirory(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Issue> getAllIssue() {
        return List.copyOf(issues);
    }
    @PostConstruct
    public void generateData(){
        issues.addAll(List.of(
                new Issue(1,1,LocalDate.now()),
                new Issue(2,2,LocalDate.now()),
                new Issue(3,2,LocalDate.now())
        ));
        Issue issue = issues.get(2);
        issue.setReturned_at(LocalDate.now());
    }
    public Issue getIssueByID(long id) {
        return issues.stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteIssue(long id) {
        for (int i = 0; i < issues.size(); i++) {
            if (issues.get(i).getId() == id) {
                issues.remove(i);
                return true;
            }

        }
        return false;
    }

    public Issue addIssue(Issue issue) {
        issues.add(issue);
        return issue;
    }
    public Issue closeIssue(long id){
        Issue issue = issues.stream()
                .filter(it -> it.getId() == id)
                .findFirst()
                .orElse(null);
        if (issue != null){
            issue.setReturned_at(LocalDate.now());
        }
        return issue;
    }
}
