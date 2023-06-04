package wsb2023.pogorzelski.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import wsb2023.pogorzelski.models.Issue;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueFilter {
    private String name;

    private Person creator;

    private String globalSearch;


    public Specification<Issue> buildSpecification() {
        return Specification.allOf(
                ilike("name", name),
                equalTo("creator", creator)
        ).and(Specification.anyOf(
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

    private Specification<Issue> ilike(String property, String value) {
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, builder) -> builder.like(builder.lower(root.get(property)), "%" + value.toLowerCase() + "%");
    }

    public String toQueryString(Integer page) {
        return "page=" + page
                + (name != null ? "&name=" + name : "")
                + (creator != null ? "&creator=" + creator.getId() : "")
                + (globalSearch != null ? "&globalSearch=" + globalSearch : "");
    }
}

