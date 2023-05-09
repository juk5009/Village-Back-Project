package shop.mtcoding.village.dto.file.dto;

import lombok.*;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileStatus;
import shop.mtcoding.village.model.place.Place;

import java.util.List;

@Getter
@Setter
public class FileSaveDTO {

//    private List<FileDTO> fileDTOS;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileSaveDto extends File {
//        private Long id;
        private String fileName;
        private String fileUrl;
        private String type;

        public File toEntity(String fileName, String fileUrl, Place place) {
            return new File(fileName, fileUrl, type, FileStatus.WAIT, place);
        }

        public File toEntity() {
            return new File(null, null, fileName, fileUrl, type, FileStatus.WAIT);
        }
    }
}