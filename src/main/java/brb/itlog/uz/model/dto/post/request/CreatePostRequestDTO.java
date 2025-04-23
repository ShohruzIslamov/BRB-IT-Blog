package brb.itlog.uz.model.dto.post.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostRequestDTO {

    List<PostDTO> posts;

}
