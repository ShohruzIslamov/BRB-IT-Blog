package brb.itlog.uz.model.dto.newsletter;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewsLetterRequestDTO {

   private List<CreateNewsLetterDTO> newsletters;

}
