package brb.itlog.uz.model.dto.post.request;

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
public class PostDTO {

    @JsonProperty("title")
    @Schema(description = "title", example = "World")
    @NotBlank(message = "title is required")
    @Size(max = 255)
    private String title;

    @JsonProperty("status")
    @Schema(description = "status", example = "draft")
    private String status;

    @JsonProperty("lexical")
    @Schema(description = "lexical", example = "Hello world")
    private String lexical;

    @JsonProperty("html")
    @Schema(description = "html", example = "Hello world")
    private String html;

    @JsonProperty("slug")
    @Schema(description = "slug", example = "http://World.brb.itblog.uz")
    private String slug;

    @JsonProperty("tags")
    @Schema(description = "tags", example = "[\n" +
            "                { \"name\": \"my tag\", \"description\": \"a very useful tag\" },\n" +
            "                { \"name\": \"#hidden\" }\n" +
            "            ]")
    private List<TagDTO> tagsDetailed;

    @JsonProperty("authors")
    @Schema(description = "authors", example = "[\"example@gew.org\", \"test@gewr.org\"]")
    private List<String> authors;

    @JsonProperty("authors_id")
    @Schema(description = "authors_id", example = " [\n" +
            "                { \"id\": \"1\" },\n" +
            "                { \"id\": \"1\" },\n" +
            "                { \"id\": \"2\" }\n" +
            "            ]")
    private List<AuthorDTO> authorsDetailed;
}
