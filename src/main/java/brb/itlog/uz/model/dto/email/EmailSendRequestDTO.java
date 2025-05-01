package brb.itlog.uz.model.dto.email;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailSendRequestDTO {

    private String email;
    private Long userId;

}
