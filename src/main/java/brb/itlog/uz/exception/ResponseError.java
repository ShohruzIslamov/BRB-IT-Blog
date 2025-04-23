package brb.itlog.uz.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"code", "message"})
public class ResponseError {

    @JsonProperty("code")
    @Schema(description = "code of error", example = "500", allowableValues = {"400", "401", "403", "404", "500"})
    private int code;

    @JsonProperty("message")
    @Schema(description = "message of error", example = "нет дествующей информация")
    private String message;
}
