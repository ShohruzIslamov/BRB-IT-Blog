package brb.itlog.uz.model.dto.members.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMembersResponseDTO {

    private List<MembersResponseDTO> members;

}
