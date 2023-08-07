package com.example.meosasshu.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException, IOException {
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> showImage(@PathVariable("filename") String filename) throws IOException {
        Path imagePath = Paths.get(System.getProperty("user.dir") + "\\files\\" + filename);
        //인자는 파일 저장경로 + 파일명임

        // Check if the file exists
        if (!Files.exists(imagePath) || Files.isDirectory(imagePath)) {
            // You can return an appropriate error response here if needed
            return ResponseEntity.notFound().build();
        }

        Resource resource = new InputStreamResource(Files.newInputStream(imagePath));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust MediaType as per the image type
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(resource);
    }
}
