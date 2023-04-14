package shop.mtcoding.village.model.hashtag;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hashtag_tb")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("해시태그 아이디")
    private Long id;

    @Comment("해시태그 이름")
    private String hashtagName;

    @Comment("공간의 아이디")
    private Long placeId;

    @Builder
    public Hashtag(String hashtagName, Long placeId) {
        this.hashtagName = hashtagName;
        this.placeId = placeId;
    }
}
