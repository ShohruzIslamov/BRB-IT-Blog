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
public class TagDTO {

    @JsonProperty("name")
    @Schema(description = "name", example = "gewr")
    private String name;

    @JsonProperty("description")
    @Schema(description = "description", example = "example@gew.org")
    private String description;
}
