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
@Table(name = "book_detail")
public class Detail {
    @Id
    private Integer id;

    private String name;

    @OneToOne(mappedBy = "detail", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("detail")
    private Book book;
}
