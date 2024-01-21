package ru.geekbrains.Library.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Library.models.Issue;
import ru.geekbrains.Library.models.IssueRequest;
import ru.geekbrains.Library.repositories.IssueReposirory;
import ru.geekbrains.Library.services.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {
    private final IssueReposirory issueReposirory;
    private final IssueService issueService;

    public IssueController(IssueReposirory issueReposirory, IssueService issueService) {
        this.issueReposirory = issueReposirory;
        this.issueService = issueService;
    }
    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable long id){
        return issueReposirory.getIssueByID(id);
    }
    @PutMapping ("/{id}")
    public Issue returnBook(@PathVariable long id){
        issueReposirory.closeIssue(id);
        return issueReposirory.getIssueByID(id);
    }
    @PutMapping()
    public ResponseEntity<?> addIssue(@RequestParam long bookID, @RequestParam long readerID){
        try {
            IssueRequest request = new IssueRequest(bookID, readerID);
            if (issueService.addIssue(request) == null)
                return new ResponseEntity<>("Книга у читателя", HttpStatus.CONFLICT);
            else return new ResponseEntity<>("Книга выдана", HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
