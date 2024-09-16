package wsb2023.pogorzelski.models;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IssueAddObject {
    @NotNull()
    @Size(min = 3, max = 100, message = "Task name must have min. 3 and max. 100 characters")
    String name;
    @NotNull()
    @Size(min = 5, message = "Description must have min. 5 characters")
    String description;
    Type type;
    Priority priority;
    Status status;
    String projectId;
    String assignToAuthor;
}
