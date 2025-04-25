package brb.itlog.uz.controller;

import brb.itlog.uz.exception.AppResponse;
import brb.itlog.uz.exception.AppResponseError;
import brb.itlog.uz.model.Empty;
import brb.itlog.uz.model.dto.members.request.CreateMembersRequestDTO;
import brb.itlog.uz.model.dto.members.request.UpdateMembersRequestDTO;
import brb.itlog.uz.model.dto.members.response.CreateMembersResponseDTO;
import brb.itlog.uz.service.MembersService;
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
@Tag(name = "BRB IT BLOG", description = "BRB IT BLOG MembersController methods")
@RequestMapping("/members")
public class MembersController {

    private final MembersService membersService;

    @Operation(summary = "Create Members method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PostMapping("/admin/create")
    public ResponseEntity<AppResponse<CreateMembersResponseDTO, Empty>> createNewsLetter(@Valid @RequestBody CreateMembersRequestDTO request) {

        CreateMembersResponseDTO response = membersService.createMember(request);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Update Members method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PutMapping("/admin/update/{memberId}")
    public ResponseEntity<AppResponse<String, Empty>> updateMembers(@PathVariable Long memberId,
                                                                       @Valid @RequestBody UpdateMembersRequestDTO request) {

        String response = membersService.updateMember(memberId, request);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }
}
