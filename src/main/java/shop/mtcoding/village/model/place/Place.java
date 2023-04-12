package shop.mtcoding.village.model.place;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place_tb")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공간 아이디")
    private Long id;
    @Comment("유저(호스트) 정보")
    @ManyToOne
    private User user;
    @Comment("공간 제목")
    private String title;
    @Comment("공간 주소")
    @OneToOne
    private Address address;
    @Comment("공간 전화번호")
    private String tel;
    @Comment("공간 리뷰정보")
    @ManyToOne
    private Review review;
    @Comment("공간 정보")
    private String placeIntroductionInfo;
    @Comment("공간 소개")
    private String guide;
    @Comment("시설 정보")
    private String facilityInfo;
    @Comment("공간 해시태그")
    private String hashtag;
    @Comment("공간 사진")
    private String image;
    @Comment("공간의 최대 인원수")
    private Integer maxPeople;
    @Comment("시간당 결제 금액")
    private Integer pricePerHour;
    @Comment("시작 시간")
    private LocalDateTime startTime;
    @Comment("마감 시간")
    private LocalDateTime endTime;
    @Comment("공간별 카테고리")
    @OneToOne
    private Category category;


}
