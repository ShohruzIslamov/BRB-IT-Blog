package brb.itlog.uz.model.entity.newsletter;

import brb.itlog.uz.model.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "newsleters")
public class Newsletters extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsletersIdGenerator")
    @SequenceGenerator(name = "newsletersIdGenerator", sequenceName = "newsleters_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_email")
    private String senderEmail;

    @Column(name = "status")
    private String status;

    @Column(name = "sender_reply_to")
    private String senderReplyTo;

    @Column(name = "subscribe_on_signup")
    private boolean subscribeOnSignup;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "header_image")
    private String headerImage;

    @Column(name = "show_header_icon")
    private boolean showHeaderIcon;

    @Column(name = "show_header_title")
    private boolean showHeaderTitle;

    @Column(name = "show_header_name")
    private boolean showHeaderName;

    @Column(name = "title_font_category")
    private String titleFontCategory;

    @Column(name = "title_alignment")
    private String titleAlignment;

    @Column(name = "show_feature_image")
    private boolean showFeatureImage;

    @Column(name = "body_font_category")
    private String bodyFontCategory;

    @Column(name = "footer_content")
    private String footerContent;

    @Column(name = "show_badge")
    private boolean showBadge;

}
