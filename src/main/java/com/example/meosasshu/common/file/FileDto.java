package com.example.meosasshu.common.file;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;
    public File toEntity() {
        File build = File.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

}
