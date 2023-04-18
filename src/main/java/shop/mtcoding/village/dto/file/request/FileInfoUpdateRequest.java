package shop.mtcoding.village.dto.file.request;

import javax.validation.constraints.NotBlank;

public class FileInfoUpdateRequest{
        @NotBlank(message = "파일 출처를 입력해주세요") // TODO 출처를 Request 에서 ? File 관련은 서비스에서 주입 ?
        private String type;

        public FileInfoUpdateRequest(String type) {
                this.type = type;
        }
}


