package shop.mtcoding.village.dto.file.dto;

import lombok.Getter;

@Getter
public class FileInfoDTO{
        private Long id;

        private String type;

        public FileInfoDTO(Long id, String type) {
                this.id = id;
                this.type = type;
        }
}
