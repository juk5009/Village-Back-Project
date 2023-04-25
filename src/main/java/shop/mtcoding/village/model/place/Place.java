package shop.mtcoding.village.model.place;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.dto.place.response.PlaceUpdateResponse;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Comment("공간 제목")
    private String title;

    @Comment("공간 주소")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;

    @Comment("공간 전화번호")
    private String tel;

    @Comment("공간 정보")
    private String placeIntroductionInfo;

    @Comment("공간 소개")
    private String notice;

    @Comment("공간 사진")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    @Comment("공간의 최대 인원수")
    private Integer maxPeople;

    @Comment("주차 가능 대수")
    private Integer maxParking;


    @Comment("시간당 결제 금액")
    private Integer pricePerHour;

    @Comment("시작 시간")
    private LocalTime startTime;

    @Comment("마감 시간")
    private LocalTime endTime;




    public Place(User user, String title, Address address, String tel, String placeIntroductionInfo, String notice, FileInfo fileInfo, Integer maxPeople,
            Integer maxParking, Integer pricePerHour, LocalTime startTime, LocalTime endTime) {
        this.user = user;
        this.title = title;
        this.address = address;
        this.tel = tel;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.fileInfo = fileInfo;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Builder
    public Place(String title, Address address, String tel, LocalTime startTime, LocalTime endTime,
                 String placeIntroductionInfo,
                 String notice, Integer maxPeople, Integer maxParking, Integer pricePerHour) {
        this.title = title;
        this.address = address;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
//        this.fileInfo = fileInfo;
    }

    public PlaceSaveResponse toResponse() {
        return new PlaceSaveResponse(
                title, address, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
        );
    }

    public PlaceUpdateResponse toUpdateResponse() {

        return new PlaceUpdateResponse(
                title, address, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
        );
    }


}