package brb.itlog.uz.model.dto.members.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMembersRequestDTO {
    private List<MembersRequestDTO> members;
}
