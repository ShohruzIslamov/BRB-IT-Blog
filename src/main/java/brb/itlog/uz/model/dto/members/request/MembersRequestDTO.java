package brb.itlog.uz.model.dto.members.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersRequestDTO {

    @JsonProperty("email")
    @Schema(description = "email", example = "SDs@gnail.com")
    @NotBlank(message = "email is required")
    @Size(max = 255)
    private String email;

    private String name;

    private String note;

    private List<LabelRequestDTO> labels;

    private List<NewsletterRequestDTO> newsletters;

}
