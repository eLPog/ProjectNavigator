package wsb2023.pogorzelski.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.Project;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFilter {

    private String name;

    private Person creator;

    private String globalSearch;


    public Specification<Project> buildSpecification() {
        return Specification.allOf(
                equalTo("enabled", true),
                ilike("name", name),
                equalTo("creator", creator)
        ).and(Specification.anyOf(
                ilike("name", globalSearch),
                ilike("description", globalSearch)
        ));
    }

    private Specification<Project> equalTo(String property, Object value) {
        if (value == null) {
            return Specification.where(null);
        }

        return (root, query, builder) -> builder.equal(root.get(property), value);
    }

    private Specification<Project> ilike(String property, String value) {
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
