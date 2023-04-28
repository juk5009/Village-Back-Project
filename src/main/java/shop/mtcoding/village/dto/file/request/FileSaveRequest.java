package shop.mtcoding.village.dto.file.request;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileStatus;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FileSaveRequest{
        @NotBlank(message = "파일 이름을 력해주세요")
        String fileName;

        @NotBlank(message = "파일 경로를 입력해주세요")
        String fileUrl;


    public File toEntity() {
        return new File(null, null , fileName, fileUrl, FileStatus.WAIT);
    }

    public FileSaveRequest(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
