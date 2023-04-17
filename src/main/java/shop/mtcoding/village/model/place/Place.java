package shop.mtcoding.village.model.place;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.dto.date.DateSaveResponse;
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
import java.time.LocalDateTime;
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
    @OneToOne
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
    @OneToOne
    @JoinColumn(name = "dayOfWeek")
    private Dates dayOfWeek;

//    @Comment("시설 정보")
//    @OneToMany()
//    private List<FacilityInfo> facilityInfo;
//
//    @Comment("공간 해시태그")
//    @OneToMany()
//    private List<Hashtag> hashtag;
//
//    @Comment("공간 사진")
//    @OneToMany()
//    private List<FileInfo> fileInfo;

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

    @Builder
    public Place(User user, String title, Address address, String tel, List<Review> review, String placeIntroductionInfo, String notice, Dates dayOfWeek
            , List<FacilityInfo> facilityInfo, List<Hashtag> hashtag, List<FileInfo> fileInfo, Integer maxPeople, Integer pricePerHour, LocalDateTime startTime
            , LocalDateTime endTime, Category category) {
        this.user = user;
        this.title = title;
        this.address = address;
        this.tel = tel;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.dayOfWeek = dayOfWeek;
//        this.review = review;
//        this.facilityInfo = facilityInfo;
//        this.hashtag = hashtag;
//        this.fileInfo = fileInfo;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
    }

    public Place(String title, Address address, String tel, LocalDateTime startTime, LocalDateTime endTime,
                 String placeIntroductionInfo,
                 Dates dayOfWeek,
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
    }

    public PlaceSaveResponse toResponse() {
        return new PlaceSaveResponse(title, address, tel, startTime, endTime, placeIntroductionInfo
                , Collections.singletonList(dayOfWeek), notice, maxPeople, pricePerHour, category);
    }



}
