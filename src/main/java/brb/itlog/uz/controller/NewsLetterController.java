package brb.itlog.uz.controller;

import brb.itlog.uz.exception.AppResponse;
import brb.itlog.uz.exception.AppResponseError;
import brb.itlog.uz.model.Empty;
import brb.itlog.uz.model.dto.newsletter.CreateNewsLetterRequestDTO;
import brb.itlog.uz.model.dto.newsletter.UpdateNewsLetterRequestDTO;
import brb.itlog.uz.service.NewsLetterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "NEWSLETTER", description = "NEWSLETTER NewsLetterController methods")
@RequestMapping("/newsletter")
public class NewsLetterController {

    private final NewsLetterService newsLetterService;

    @Operation(summary = "Create Newsletter method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PostMapping("/admin/create")
    public ResponseEntity<AppResponse<String, Empty>> createNewsLetter(@RequestParam(defaultValue = "false") Boolean opt_in_existing, @Valid @RequestBody CreateNewsLetterRequestDTO request) {

        String response = newsLetterService.createNewsLetter(request);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Update Newsletter method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PutMapping("/admin/update/{newsletterId}")
    public ResponseEntity<AppResponse<String, Empty>> updateNewsletter(@PathVariable Long newsletterId,
                                                                       @Valid @RequestBody UpdateNewsLetterRequestDTO request) {

        String response = newsLetterService.updateNewsLetter(newsletterId, request);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    // Todo get all
}
