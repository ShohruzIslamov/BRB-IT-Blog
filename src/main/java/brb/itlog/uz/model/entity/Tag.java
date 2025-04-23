package brb.itlog.uz.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tagsIdGenerator")
    @SequenceGenerator(name = "tagsIdGenerator", sequenceName = "tags_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;

    }
}
