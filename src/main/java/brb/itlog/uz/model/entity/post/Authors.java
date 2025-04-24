package brb.itlog.uz.model.entity.post;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "authors")
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorIdGenerator")
    @SequenceGenerator(name = "authorIdGenerator", sequenceName = "author_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "authors")
    private List<Posts> posts;
}
