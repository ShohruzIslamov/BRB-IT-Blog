package brb.itlog.uz.model.dto.newsletter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewsLetterDTO {

    @JsonProperty("name")
    @Schema(description = "name", example = "My newly created newsletter")
    @NotBlank(message = "name is required")
    private String name;

    @JsonProperty("description")
    @Schema(description = "description", example = "This is a newsletter description")
    private String description;

    @JsonProperty("status")
    @Schema(description = "status", example = "active")
    private String status;

    @JsonProperty("sender_reply_to")
    @Schema(description = "sender_reply_to", example = "newsletter")
    private String senderReplyTo;

    @JsonProperty("subscribe_on_signup")
    @Schema(description = "subscribe_on_signup", example = "true")
    private boolean subscribeOnSignup;

    @JsonProperty("show_header_icon")
    @Schema(description = "show_header_icon", example = "true")
    private boolean showHeaderIcon;

    @JsonProperty("show_header_title")
    @Schema(description = "show_header_title", example = "true")
    private boolean showHeaderTitle;

    @JsonProperty("show_header_name")
    @Schema(description = "show_header_name", example = "true")
    private boolean showHeaderName;

    @JsonProperty("title_font_category")
    @Schema(description = "title_font_category", example = "sans_serif")
    private String titleFontCategory;

    @JsonProperty("title_alignment")
    @Schema(description = "title_alignment", example = "center")
    private String titleAlignment;

    @JsonProperty("show_feature_image")
    @Schema(description = "show_feature_image", example = "true")
    private boolean showFeatureImage;

    @JsonProperty("body_font_category")
    @Schema(description = "body_font_category", example = "sans_serif")
    private String bodyFontCategory;

    @JsonProperty("show_badge")
    @Schema(description = "show_badge", example = "true")
    private boolean showBadge;

}
