package brb.itlog.uz.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"success", "done", "error"})
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Standard error response")
public class AppResponseError {

    @JsonProperty("success")
    @Schema(description = "status of response", example = "false")
    private boolean success;

    @JsonProperty("done")
    private Object done;

    @JsonProperty("error")
    private ResponseError error;

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Throwable e) {
            return e.getMessage();
        }
    }
}
