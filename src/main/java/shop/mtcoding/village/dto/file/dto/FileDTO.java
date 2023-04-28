package shop.mtcoding.village.dto.file.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO{

        private Long id;

        private FileInfoDTO fileInfo;

        private String fileName;

        private String fileUrl;

        private String status;

        public FileDTO(Long id, FileInfoDTO fileInfo, String fileName, String fileUrl, String status) {
                this.id = id;
                this.fileInfo = fileInfo;
                this.fileName = fileName;
                this.fileUrl = fileUrl;
                this.status = status;
        }
}
