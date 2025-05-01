package brb.itlog.uz.model.entity.label;

import brb.itlog.uz.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "labels")
public class Label extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "labelsIdGenerator")
    @SequenceGenerator(name = "labelsIdGenerator", sequenceName = "labels_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;
}
