package brb.itlog.uz.model.entity.image;

import brb.itlog.uz.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "images")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename", nullable = false, length = 255)
    private String filename;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "ref", length = 255)
    private String ref;

}
