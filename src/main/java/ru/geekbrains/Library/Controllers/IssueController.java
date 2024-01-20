package ru.geekbrains.Library.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Library.Models.Issue;
import ru.geekbrains.Library.Models.IssueRequest;
import ru.geekbrains.Library.Repositories.IssueReposirory;
import ru.geekbrains.Library.Services.IssueService;

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
    /*
    @PutMapping
    public Issue addIssue(@RequestParam long bookID, @RequestParam long readerID){
        IssueRequest request = new IssueRequest(bookID, readerID);
        return issueService.addIssue(request);
    }

     */
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
