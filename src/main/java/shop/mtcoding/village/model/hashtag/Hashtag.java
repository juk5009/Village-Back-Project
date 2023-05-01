package shop.mtcoding.village.model.hashtag;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @Column(name = "hashtag_name")
    private String hashtagName;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Place place;

    @Builder
    public Hashtag(String hashtagName, Place place) {
        this.hashtagName = hashtagName;
        this.place = place;
    }

    public Hashtag(Long id, String hashtagName) {
        this.id = id;
        this.hashtagName = hashtagName;
    }
}
