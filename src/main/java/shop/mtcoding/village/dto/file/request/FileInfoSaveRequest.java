package shop.mtcoding.village.dto.file.request;

import lombok.Getter;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.file.FileType;

import javax.validation.constraints.NotBlank;

@Getter
public class FileInfoSaveRequest{
    @NotBlank(message = "파일 출처를 입력해주세요") // TODO 출처를 Request 에서 ?
    private String type;

    public FileInfo toEntity() {
            return new FileInfo(null, FileType.PLACE);
        }

    public FileInfoSaveRequest(String type) {
        this.type = type;
    }
}
