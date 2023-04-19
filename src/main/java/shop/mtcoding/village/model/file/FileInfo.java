package shop.mtcoding.village.model.file;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.core.jpa.BaseTime;
import shop.mtcoding.village.dto.file.dto.FileInfoDTO;
import shop.mtcoding.village.dto.file.response.FileInfoResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file_info_tb")
public class FileInfo extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Long id;

    @Comment("파일 출처")
    @Enumerated(EnumType.STRING)
    private FileType type;


    @Builder
    public FileInfo(FileType type) {
        this.type = type;
    }

    public FileInfoDTO toDTO() {
        return new FileInfoDTO(id, type.name());
    }

    public FileInfoResponse toResponse() {
        return new FileInfoResponse(id, type.name());
    }
}
