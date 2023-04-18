package shop.mtcoding.village.model.file;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fileInfo_tb")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Long id;

    @Comment("파일 출처")
    private FileType type;

    @Comment("공간의 아이디")
    private Long placeId;

    @Builder
    public FileInfo(FileType type, Long placeId) {
        this.type = type;
        this.placeId = placeId;
    }
}
