package brb.itlog.uz.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ImageUploadService {

    List<Map<String, Object>> uploadFiles(List<MultipartFile> files, String ref, String purpose) throws IOException;

}
