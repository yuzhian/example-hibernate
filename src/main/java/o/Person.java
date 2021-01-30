package o;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author yuzhian
 */
@Getter
@Setter
@Entity
public class Person {
    @Id
    private Integer id;
    @Column(length = 20)
    private String name;
    private LocalDate birthday;
}
