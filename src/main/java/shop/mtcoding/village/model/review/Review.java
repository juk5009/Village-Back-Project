package shop.mtcoding.village.model.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_tb")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("리뷰 아이디")
    private Long id;
    @Comment("유저 정보")
    @ManyToOne
    private User user;
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
}
