package wsb2023.pogorzelski.models;

import lombok.Data;

@Data
public class IssueAddObject {
    String name;
    String description;
    Type type;
    Priority priority;
    Status status;
    String projectId;
}
