package shop.mtcoding.village.api.s3;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "gallery")
public class Gallery {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String filePath;

//    private String file;

    @Builder
    public Gallery(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }
}

