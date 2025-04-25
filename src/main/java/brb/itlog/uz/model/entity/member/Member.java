package brb.itlog.uz.model.entity.member;

import brb.itlog.uz.model.entity.base.BaseEntity;
import brb.itlog.uz.model.entity.label.Label;
import brb.itlog.uz.model.entity.newsletter.Newsletter;
import brb.itlog.uz.model.entity.subscription.Subscription;
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
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membersIdGenerator")
    @SequenceGenerator(name = "membersIdGenerator", sequenceName = "members_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "uuid")
    private String uuid; // public ID

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "geolocation")
    private String geolocation;

    @Column(name = "avatar_image")
    private String avatarImage;

    @Column(name = "status")
    private String status; // "free" or "paid"

    @Column(name = "last_seen_at")
    private LocalDateTime lastSeenAt;

    @Column(name = "email_count")
    private int emailCount;

    @Column(name = "email_opened_count")
    private int emailOpenedCount;

    @Column(name = "email_open_rate")
    private Double emailOpenRate;

    // Many-to-Many with Labels
    @ManyToMany
    @JoinTable(
            name = "member_labels",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels;

    // Many-to-Many with Newsletters
    @ManyToMany
    @JoinTable(
            name = "member_newsletters",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "newsletter_id")
    )
    private List<Newsletter> newsletters;

    // One-to-Many with Subscriptions
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions;

}
