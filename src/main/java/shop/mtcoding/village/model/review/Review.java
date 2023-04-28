package shop.mtcoding.village.model.review;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "review_tb")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("리뷰 아이디")
    private Long id;

    @Comment("유저 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "place_id")
    private Place place;

    @Comment("별점")

    private Integer starRating;

    @Comment("리뷰 내용")
    private String content;

    @Comment("리뷰 사진")
    private String image;

    @Comment("좋아요 수")
    private Integer likeCount;

    @Comment("리뷰 시간")
    private Timestamp createdAt;

    @Builder
    public Review(User user, Integer starRating, String content, String image, Integer likeCount) {
        this.user = user;
        this.starRating = starRating;
        this.content = content;
        this.image = image;
        this.likeCount = likeCount;
    }
}
