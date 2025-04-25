package brb.itlog.uz.model.entity.subscription;

import brb.itlog.uz.model.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionsIdGenerator")
    @SequenceGenerator(name = "subscriptionsIdGenerator", sequenceName = "subscriptions_id_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
