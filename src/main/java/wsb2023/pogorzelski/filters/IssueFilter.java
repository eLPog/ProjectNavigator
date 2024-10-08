package wsb2023.pogorzelski.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import wsb2023.pogorzelski.models.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueFilter {
    private Project project;
    private Status status;
    private Person assignee;
    private String globalSearch;
    private String description;
    private Priority priority;


    public Specification<Issue> buildSpecification() {
        Long projectId = project != null ? project.getId() : null;
        return Specification.allOf(
                equalTo("project", "id", projectId),
                equalTo("status", status),
                equalTo("priority", priority),
                ilike("description",description),
                equalTo("assignee", assignee))
                .and(Specification.anyOf(
                        ilike("name", globalSearch),
                        ilike("description", globalSearch)
                ));
    }

    private Specification<Issue> equalTo(String property, Object value) {
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, builder) -> builder.equal(root.get(property), value);
    }

    private Specification<Issue> equalTo(String property, String relProperty, Object value) {
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, builder) -> builder.equal(root.get(property).get(relProperty), value);
    }

    private Specification<Issue> ilike(String property, String value) {
        if (value == null) {
            return Specification.where(null);
        }
        return (root, query, builder) -> builder.like(builder.lower(root.get(property)), "%" + value.toLowerCase() + "%");
    }
}

