package shop.mtcoding.village.dto.file.request;

import javax.validation.constraints.NotBlank;

public class FileInfoUpdateRequest{
        @NotBlank(message = "파일 출처를 입력해주세요")
        private String type;

        public FileInfoUpdateRequest(String type) {
                this.type = type;
        }
}


