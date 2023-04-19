package shop.mtcoding.village.dto.file.response;

import lombok.Getter;

@Getter
public class FileInfoResponse{
        private Long id;

        private String type;

        public FileInfoResponse(Long id, String type) {
                this.id = id;
                this.type = type;
        }
}
