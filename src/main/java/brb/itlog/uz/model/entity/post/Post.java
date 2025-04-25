package brb.itlog.uz.model.entity.post;

import brb.itlog.uz.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postsIdGenerator")
    @SequenceGenerator(name = "postsIdGenerator", sequenceName = "posts_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "lexical", columnDefinition = "TEXT")
    private String lexical;

    @Column(name = "html", columnDefinition = "TEXT")
    private String html;

    @Column(name = "status")
    private String status;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "post_authors",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
}
