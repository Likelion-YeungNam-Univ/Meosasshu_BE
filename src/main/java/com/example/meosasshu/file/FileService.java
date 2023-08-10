package com.example.meosasshu.file;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public FileDto uploadFile(MultipartFile file) throws IOException, NoSuchAlgorithmException {

        String origFilename = file.getOriginalFilename();
        String filename = new MD5Generator(origFilename).toString();
        /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
        String savePath = System.getProperty("user.dir") + "\\files";
        /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
        if (!new java.io.File(savePath).exists()) {
            try{
                new java.io.File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + filename;
        file.transferTo(new java.io.File(filePath));

        FileDto fileDto = new FileDto();
        fileDto.setOrigFilename(origFilename);
        fileDto.setFilename(filename);
        fileDto.setFilePath(filePath);
        Long fileId = saveFile(fileDto);
        fileDto.setId(fileId);

        return fileDto;
    }
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }

    public String getFilePathFromFile(File file) {
        return "/images/"+file.getFilename();
    }
}
