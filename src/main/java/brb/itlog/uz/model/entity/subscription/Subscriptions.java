package brb.itlog.uz.model.entity.subscription;

import brb.itlog.uz.model.entity.member.Members;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionsIdGenerator")
    @SequenceGenerator(name = "subscriptionsIdGenerator", sequenceName = "subscriptions_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;
}
