package brb.itlog.uz.model.dto.newsletter;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewsLetterRequestDTO {

   @Valid
   private List<CreateNewsLetterDTO> newsletters;

}
