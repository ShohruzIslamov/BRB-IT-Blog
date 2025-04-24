package brb.itlog.uz.model.dto.members.response;

import brb.itlog.uz.model.dto.members.request.LabelRequestDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersResponseDTO {

    private String id;
    private String uuid;
    private String email;
    private String name;
    private String note;
    private String geolocation;
    private String createdAt;
    private String  updatedAt;
    private List<LabelResponseDTO> labels;
    private List<SubscriptionResponseDTO> subscriptions;
    private String avatarImage;
    private int emailCount;
    private int emailOpenedCount;
    private Double emailOpenRate;
    private String status;
    private String lastSeenAt;
    private List<NewsletterResponseDTO> newsletters;

}
