package brb.itlog.uz.controller;

import brb.itlog.uz.exception.AppResponse;
import brb.itlog.uz.exception.AppResponseError;
import brb.itlog.uz.model.Empty;
import brb.itlog.uz.model.dto.email.EmailSendRequestDTO;
import brb.itlog.uz.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "MAIL SEND", description = "MAIL SEND MailController methods")
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Operation(summary = "Email Send for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PostMapping("/send/link")
    public ResponseEntity<AppResponse<String, Empty>> sendVerificationEmail(@RequestBody EmailSendRequestDTO request) {

        String response = mailService.send(request);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }


}
