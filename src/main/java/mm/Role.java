package mm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author yuzhian
 */
@Getter
@Setter
@Entity
public class Role {
    @Id
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "rid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "uid", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("roles")
    private Set<User> users;
}
