package shop.mtcoding.village.model.hashtag;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place placeId;

    @Builder
    public Hashtag(List<String> hashtagNames, Place placeId) {
        this.hashtagName = hashtagNames.toString();
        this.placeId = placeId;
    }
}
