package brb.itlog.uz.model.dto.members.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelRequestDTO {

    private String name;

    private String slug;
}
