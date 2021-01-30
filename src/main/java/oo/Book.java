package oo;

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
@Table(name = "book")
public class Book {
    @Id
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "did")
    @JsonIgnoreProperties("book")
    private Detail detail;
}
