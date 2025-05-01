package brb.itlog.uz.model.dto.post.request;

import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequestDTO {

    @Valid
    List<PostDTO> posts;

}
