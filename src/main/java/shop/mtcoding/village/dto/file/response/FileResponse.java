package shop.mtcoding.village.dto.file.response;

import shop.mtcoding.village.dto.file.dto.FileInfoDTO;

public class FileResponse{
        private Long id;

        private FileInfoDTO fileInfo;

        private String fileName;

        private String fileUrl;

        private String status;

        public FileResponse(Long id, FileInfoDTO fileInfo, String fileName, String fileUrl, String status) {
                this.id = id;
                this.fileInfo = fileInfo;
                this.fileName = fileName;
                this.fileUrl = fileUrl;
                this.status = status;
        }
}
