package om;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author yuzhian
 */
@Getter
@Setter
@Entity
public class Employee {
    @Id
    private Integer id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    @JsonIgnoreProperties("employees")
    private Department department;
}
