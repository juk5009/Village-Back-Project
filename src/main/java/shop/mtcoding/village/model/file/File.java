package shop.mtcoding.village.model.file;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.dto.file.dto.FileDTO;
import shop.mtcoding.village.dto.file.response.FileResponse;
import shop.mtcoding.village.model.place.Place;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file_tb")
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Long id;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Place place;

    @Comment("파일 출처")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_info_id")
    @JsonIgnore
    private FileInfo fileInfo;

    @Comment("파일 이름")
    private String fileName;

    @Comment("파일 경로")
    @Column(columnDefinition = "LONGTEXT", name = "file_url")
    private String fileUrl;

    @Comment("확장자")
    @JsonIgnore
    private String extension;

    @Comment("사진 활성화 상태")
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private FileStatus status;

    @Builder
    public File(Long id, FileInfo fileInfo, String fileName, String fileUrl, String extension, FileStatus status) {
        this.id = id;
        this.fileInfo = fileInfo;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.extension = extension;
        this.status = status;
    }

    public File(Long id, String fileUrl) {
        this.id = id;
        this.fileUrl = fileUrl;
    }

    public FileDTO toDTO() {
        return new FileDTO(id, fileInfo.toDTO(), fileName, fileUrl, extension, status.name());
    }

    public FileResponse toResponse() {
        return new FileResponse(id, fileInfo.toDTO(), fileName, fileUrl, extension, status.name());
    }
}
