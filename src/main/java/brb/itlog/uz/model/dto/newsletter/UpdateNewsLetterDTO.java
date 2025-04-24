package brb.itlog.uz.model.dto.newsletter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNewsLetterDTO {

    @JsonProperty("id")
    @Schema(description = "Newsletter ID", example = "1")
    private Long id;

    @JsonProperty("name")
    @Schema(description = "Newsletter name", example = "My newly created newsletter")
    private String name;

    @JsonProperty("description")
    @Schema(description = "Newsletter description", example = "This is an edited newsletter description")
    private String description;

    @JsonProperty("sender_name")
    @Schema(description = "Sender's name", example = "Daily Newsletter")
    private String senderName;

    @JsonProperty("sender_email")
    @Schema(description = "Sender's email", example = "null")
    private String senderEmail;

    @JsonProperty("sender_reply_to")
    @Schema(description = "Sender reply-to type", example = "newsletter")
    private String senderReplyTo;

    @JsonProperty("status")
    @Schema(description = "Status of the newsletter", example = "active")
    private String status;

    @JsonProperty("subscribe_on_signup")
    @Schema(description = "Subscribe new members on signup", example = "true")
    private Boolean subscribeOnSignup;

    @JsonProperty("sort_order")
    @Schema(description = "Sort order of newsletter", example = "1")
    private Integer sortOrder;

    @JsonProperty("header_image")
    @Schema(description = "Header image URL", example = "null")
    private String headerImage;

    @JsonProperty("show_header_icon")
    @Schema(description = "Show header icon", example = "true")
    private Boolean showHeaderIcon;

    @JsonProperty("show_header_title")
    @Schema(description = "Show header title", example = "true")
    private Boolean showHeaderTitle;

    @JsonProperty("title_font_category")
    @Schema(description = "Font category for title", example = "sans_serif")
    private String titleFontCategory;

    @JsonProperty("title_alignment")
    @Schema(description = "Title alignment", example = "center")
    private String titleAlignment;

    @JsonProperty("show_feature_image")
    @Schema(description = "Show feature image", example = "true")
    private Boolean showFeatureImage;

    @JsonProperty("body_font_category")
    @Schema(description = "Font category for body", example = "sans_serif")
    private String bodyFontCategory;

    @JsonProperty("footer_content")
    @Schema(description = "Footer content", example = "null")
    private String footerContent;

    @JsonProperty("show_badge")
    @Schema(description = "Show badge", example = "true")
    private Boolean showBadge;

    @JsonProperty("show_header_name")
    @Schema(description = "Show header name", example = "true")
    private Boolean showHeaderName;
}
