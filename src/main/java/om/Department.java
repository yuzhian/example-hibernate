package om;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * @author yuzhian
 */
@Getter
@Setter
@Entity
public class Department {
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "department")
    // @OneToMany(cascade = {CascadeType.ALL})
    // @JoinColumn(name = "dept_id")
    @JsonIgnoreProperties("department")
    private Set<Employee> employees;
}
