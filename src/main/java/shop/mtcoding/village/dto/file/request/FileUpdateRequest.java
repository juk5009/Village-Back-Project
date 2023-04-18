package shop.mtcoding.village.dto.file.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class FileUpdateRequest{

        @NotBlank(message = "파일 이름을 입력해주세요")
        private String fileName;

        @NotBlank(message = "파일 출처를 입력해주세요")
        private String fileUrl;

//        @ValueOfEnum(enumClass = FileStatus.class, message = "파일 상태 값에 이상이 있습니다. 확인해주세요")
        private String status;

        public FileUpdateRequest(String fileName, String fileUrl, String status) {
                this.fileName = fileName;
                this.fileUrl = fileUrl;
                this.status = status;
        }
}
