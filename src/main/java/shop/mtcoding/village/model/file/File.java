package shop.mtcoding.village.model.file;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.core.jpa.BaseTime;
import shop.mtcoding.village.dto.file.dto.FileDTO;
import shop.mtcoding.village.dto.file.response.FileResponse;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file_tb")
public class File extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Long id;

    @Comment("파일 출처")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;
    
    @Comment("파일 이름")
    private String fileName;

    @Comment("파일 경로")
    private String fileUrl;

    @Comment("사진 활성화 상태")
    @Enumerated(EnumType.STRING)
    private FileStatus status;

    @Builder
    public File(Long l, FileInfo fileInfo, String fileName, String fileUrl, FileStatus status) {
        this.fileInfo = fileInfo;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.status = status;
    }
    
    public FileDTO toDTO() {
        return new FileDTO(id, fileInfo.toDTO(), fileName, fileUrl, status.name());
    }

    public FileResponse toResponse() {
        return new FileResponse(id, fileInfo.toDTO(), fileName, fileUrl, status.name());
    }
}
