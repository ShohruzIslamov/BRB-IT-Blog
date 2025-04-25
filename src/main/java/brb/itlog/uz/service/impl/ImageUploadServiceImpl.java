package brb.itlog.uz.service.impl;

import brb.itlog.uz.exception.AppBadException;
import brb.itlog.uz.repository.ImageRepository;
import brb.itlog.uz.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import brb.itlog.uz.model.entity.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private final ImageRepository imageRepository;
    private final String uploadDir = "uploads";

    @Override
    public List<Map<String, Object>> uploadFiles(List<MultipartFile> files, String ref, String purpose) throws IOException {
        List<Map<String, Object>> imageList = new ArrayList<>();

        // 50 MB = 50 * 1024 * 1024 bytes
        long maxTotalSize = 50L * 1024 * 1024;
        long totalSize = files.stream().mapToLong(MultipartFile::getSize).sum();

        if (totalSize > maxTotalSize) {
            log.error("Images Size must be maximum size 50 MB. Current size: {} MB", totalSize / (1024 * 1024));
            throw new AppBadException("Images Size must be maximum size 50 MB");
        }

        Path uploadPath = Path.of(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String originalFilename = file.getOriginalFilename();
            String sanitizedFilename = UUID.randomUUID() + "-" + originalFilename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            Path filePath = uploadPath.resolve(sanitizedFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            String fileUrl = baseUrl + "/" + uploadDir + "/" + sanitizedFilename;

            Image image = new Image();
            image.setFilename(sanitizedFilename);
            image.setUrl(fileUrl);
            image.setRef(ref != null ? ref : sanitizedFilename);
            imageRepository.save(image);

            Map<String, Object> imageObject = new HashMap<>();
            imageObject.put("url", fileUrl);
            imageObject.put("ref", ref != null ? ref : sanitizedFilename);

            imageList.add(imageObject);
        }

        return imageList;
    }

}
