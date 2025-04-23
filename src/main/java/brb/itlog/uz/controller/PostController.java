package brb.itlog.uz.controller;

import brb.itlog.uz.exception.AppResponse;
import brb.itlog.uz.exception.AppResponseError;
import brb.itlog.uz.model.Empty;
import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.pagination.MetaDTO;
import brb.itlog.uz.service.PostService;
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
@Tag(name = "BRB IT BLOG", description = "BRB IT BLOG PostController methods")
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Operation(summary = "Create Post method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PostMapping("/admin/create/posts")
    public ResponseEntity<AppResponse<String, Empty>> createPosts(@Valid @RequestBody CreatePostRequestDTO request) {

        String response = postService.createPost(request);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }


    @Operation(summary = "Copy Post method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PostMapping("/{id}/copy")
    public ResponseEntity<AppResponse<PostDTO, Empty>> copyPost(@PathVariable Long id) {

        PostDTO response = postService.copyPost(id);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Update Post Title method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PutMapping("/admin/update/title/{postId}")
    public ResponseEntity<AppResponse<String, Empty>> updatePostsTitle(@PathVariable Long postId,
                                                                       @Valid @RequestBody String newTitle) {

        String response = postService.updateTitle(postId, newTitle);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Update Post Status 'PUBLISHED' method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PutMapping("/admin/update/status/published/{postId}")
    public ResponseEntity<AppResponse<String, Empty>> updatePostsStatusPublished(@PathVariable Long postId, String status) {

        String response = postService.updatePostStatusPublished(postId, status);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Update Post Status 'SCHEDULED' method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PutMapping("/admin/update/status/scheduled/{postId}")
    public ResponseEntity<AppResponse<String, Empty>> updatePostsStatusScheduled(@PathVariable Long postId, String status, String publishedAt) {

        String response = postService.updatePostStatusScheduled(postId, status, publishedAt);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Delete Post method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @DeleteMapping("/admin/delete/{postId}")
    public ResponseEntity<AppResponse<String, Empty>> deletePost(@PathVariable Long postId) {

        String response = postService.deletePost(postId);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

    @Operation(summary = "Get All Posts method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @GetMapping("/get")
    public ResponseEntity<AppResponse<MetaDTO, Object>> getAll(@RequestParam(defaultValue = "1") int pageNumber,
                                                               @RequestParam(defaultValue = "3") int pageSize) {

        MetaDTO response = postService.getAllPosts(pageNumber - 1, pageSize);

        return ResponseEntity.ok(new AppResponse<>(true, response, null));
    }

}
