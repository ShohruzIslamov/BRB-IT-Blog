package brb.itlog.uz.controller;

import brb.itlog.uz.exception.AppResponseError;
import brb.itlog.uz.service.ImageUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "BRB IT BLOG", description = "BRB IT BLOG ImageUploadController methods")
@RequestMapping("/images")
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @Operation(summary = "Upload Images method for BRB IT BLOG")
    @ApiResponse(responseCode = "200", description = "Successfully !")
    @ApiResponse(responseCode = "500", description = "Internal Server Error !",
            content = @Content(schema = @Schema(implementation = AppResponseError.class)))
    @PostMapping("/admin/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("files") List<MultipartFile> files,
                                          @RequestParam(value = "ref", required = false) String ref,
                                          @RequestParam(value = "purpose", defaultValue = "image") String purpose) {
        if (files == null || files.isEmpty()) {
            log.error("No images found or Image is Empty");
            return ResponseEntity.badRequest().body("Files are empty");
        }

        try {
            List<Map<String, Object>> imageList = imageUploadService.uploadFiles(files, ref, purpose);

            Map<String, Object> response = new HashMap<>();
            response.put("images", imageList);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading files: " + e.getMessage());
        }
    }
}

