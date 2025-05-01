package brb.itlog.uz.model.dto.members.request;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMembersRequestDTO {

    @Valid
    private List<MembersRequestDTO> members;
}
