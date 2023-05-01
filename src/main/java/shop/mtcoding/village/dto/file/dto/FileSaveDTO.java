package shop.mtcoding.village.dto.file.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileStatus;

import java.util.List;

@Getter
@Setter
public class FileSaveDTO {

    private List<FileDTO> fileDTOS;

    @Setter
    @Getter
    @ToString
    public static class FileDTO extends File {
        private Long id;
        private String fileName;
        private String fileUrl;
        private String extension;

        public File toEntity(Long id, String fileName, String fileUrl) {
            return new File(id, null, fileName, fileUrl, extension, FileStatus.WAIT);
        }

        public File toEntity() {
            return new File(id, null, fileName, fileUrl, extension, FileStatus.WAIT);
        }
    }
}