package shop.mtcoding.village.model.file;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file_tb")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("파일 출처")
    @ManyToOne
    private FileInfo fileInfo;
    
    @Comment("파일 이름")
    private String fileName;

    @Comment("파일 경로")
    private String fileUrl;

    // Multi
    // File Insert
    // S3
    // File update url

    // 확장자 추가
//    jpg

    // 파일 크기
//    3MB

    @Comment("사진 활성화 상태")
    private FileStatus status;
}
