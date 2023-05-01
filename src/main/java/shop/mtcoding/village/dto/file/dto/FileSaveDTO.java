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
        private String name;
        private String data;
        private String type;

        public File toEntity(Long id, String fileName, String fileUrl) {
            return new File(id, null, fileName, fileUrl, type, FileStatus.WAIT);
        }

        public File toEntity() {
            return new File(id, null, name, data, type, FileStatus.WAIT);
        }
    }
}