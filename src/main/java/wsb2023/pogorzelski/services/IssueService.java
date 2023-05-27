package wsb2023.pogorzelski.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Issue;
import wsb2023.pogorzelski.models.IssueAddObject;
import wsb2023.pogorzelski.repositories.IssueRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class IssueService {

    final private  ProjectService projectService;
    final private UtilService utilService;
    final private PersonService personService;

    final private IssueRepository issueRepository;

    public void addIssueToProject(IssueAddObject newIssue) {
        String userName = utilService.checkLoggedUserName();

        Issue issue = new Issue();
        issue.setProject(projectService.findProjectById( Long.parseLong(newIssue.getProjectId())));
        issue.setStatus(newIssue.getStatus());
        issue.setPriority(newIssue.getPriority());
        issue.setType(newIssue.getType());
        issue.setName(newIssue.getName());
        issue.setDescription(newIssue.getDescription());
        issue.setCreator(personService.findUserByName(userName));
        issue.setDateCreated(new Date());
        issue.setLastUpdate(new Date());
        issueRepository.save(issue);

    }


}
