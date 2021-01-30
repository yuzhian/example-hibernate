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
public class User {
    @Id
    private Integer id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "uid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rid", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("users")
    private Set<Role> roles;
}
