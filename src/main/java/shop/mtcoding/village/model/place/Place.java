package shop.mtcoding.village.model.place;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Comment("공간 전화번호")
    private String tel;

//    @Comment("공간 리뷰정보")
//    @OneToMany
//    private List<Review> review;

    @Comment("공간 정보")
    private String placeIntroductionInfo;

    @Comment("공간 소개")
    private String notice;

    @Comment("요일 정보")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dayOfWeek")
    private Dates dayOfWeek;

    @Comment("시설 정보")
    @ManyToOne(cascade = CascadeType.ALL)
    private FacilityInfo facilityInfo;

    @Comment("공간 해시태그")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hashtag")
    private Hashtag hashtag;

    @Comment("공간 사진")
    @ManyToOne(cascade = CascadeType.ALL)
    private FileInfo fileInfo;

    @Comment("공간의 최대 인원수")
    private Integer maxPeople;

    @Comment("시간당 결제 금액")
    private Integer pricePerHour;

    @Comment("시작 시간")
    private LocalTime startTime;

    @Comment("마감 시간")
    private LocalTime endTime;

    @Comment("공간별 카테고리")
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @Builder
    public Place(User user, String title, Address address, String tel, List<Review> review, String placeIntroductionInfo, String notice, Dates dayOfWeek
            , FacilityInfo facilityInfo, Hashtag hashtag, FileInfo fileInfo, Integer maxPeople, Integer pricePerHour, LocalTime startTime
            , LocalTime endTime, Category category) {
        this.user = user;
        this.title = title;
        this.address = address;
        this.tel = tel;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.dayOfWeek = dayOfWeek;
//        this.review = review;
        this.facilityInfo = facilityInfo;
        this.hashtag = hashtag;
        this.fileInfo = fileInfo;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

    public Place(String title, Address address, String tel, LocalTime startTime, LocalTime endTime,
                 String placeIntroductionInfo,
                 Dates dayOfWeek, Hashtag hashtag, FacilityInfo facilityInfo,
                 String notice, Integer maxPeople, Category category, Integer pricePerHour) {
        this.title = title;
        this.address = address;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.category = category;
        this.dayOfWeek = dayOfWeek;
        this.hashtag = hashtag;
//        this.fileInfo = fileInfo;
        this.facilityInfo = facilityInfo;
    }

    public PlaceSaveResponse toResponse() {
        return new PlaceSaveResponse(title, address, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, Collections.singletonList(dayOfWeek)
                , Collections.singletonList(hashtag), Collections.singletonList(facilityInfo), notice, maxPeople, pricePerHour, category);
    }



}
