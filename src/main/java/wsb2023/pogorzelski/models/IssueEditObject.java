package wsb2023.pogorzelski.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueEditObject {
    String name;
    String description;
    Type type;
    Status status;
    Priority priority;
}
