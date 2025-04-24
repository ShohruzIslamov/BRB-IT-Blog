package brb.itlog.uz.model.dto.members.response;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsletterResponseDTO {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sender_name")
    private String senderName;
}
