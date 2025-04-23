package brb.itlog.uz.model.dto.post.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {

    @JsonProperty("id")
    @Schema(description = "id", example = "1")
    private String id;

}
