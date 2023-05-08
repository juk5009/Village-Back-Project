package shop.mtcoding.village.model.place;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.dto.host.HostDTO;
import shop.mtcoding.village.dto.place.response.DetailPlaceResponse;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.dto.place.response.PlaceUpdateResponse;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.scrap.Scrap;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PlaceStatus;


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
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @Comment("호스트 주소")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private PlaceAddress address;

    @Comment("공간 제목")
    private String title;

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
    private LocalDateTime startTime;

    @Comment("마감 시간")
    private LocalDateTime endTime;

    @Comment("공간상태")
    @Enumerated(EnumType.STRING)
    private PlaceStatus status;

    @Comment("예약승인 필요여부")
    private Boolean isConfirmed;


    @Builder
    public Place(User user, PlaceAddress address, String title, String tel, String placeIntroductionInfo, String notice, FileInfo fileInfo
            , Integer maxPeople, Integer maxParking, Integer pricePerHour, LocalDateTime startTime, LocalDateTime endTime, PlaceStatus status, Boolean isConfirmed) {
        this.user = user;
        this.address = address;
        this.title = title;
        this.tel = tel;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.fileInfo = fileInfo;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.isConfirmed = isConfirmed;
    }

    public Place(User user, String title, String tel, String placeIntroductionInfo, String notice, FileInfo fileInfo, Integer maxPeople,
                 Integer maxParking, Integer pricePerHour, LocalDateTime startTime, LocalDateTime endTime, Boolean isConfirmed) {
        this.user = user;
        this.title = title;
        this.tel = tel;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.fileInfo = fileInfo;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isConfirmed = isConfirmed;
    }


    public Place(String title, String tel, PlaceAddress address, LocalDateTime startTime, LocalDateTime endTime,
                 String placeIntroductionInfo,
                 String notice, Integer maxPeople, Integer maxParking, Integer pricePerHour, Boolean isConfirmed) {
        this.title = title;
        this.tel = tel;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.isConfirmed = isConfirmed;
//        this.fileInfo = fileInfo;
    }

    public PlaceSaveResponse toResponse() {
        return new PlaceSaveResponse(
                title, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
        );
    }

    public PlaceUpdateResponse toUpdateResponse() {
        return new PlaceUpdateResponse(
                title, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
        );
    }

    public DetailPlaceResponse toDetailResponse(
            List<File> file, Host host, List<Review> review, Integer scrap, List<Hashtag> hashtags, List<FacilityInfo> facilitys, List<Dates> dayOfWeeks, String categoryName
    ) {
        HostDTO hostDTO = new HostDTO();
        hostDTO.setId(host.getId());
        hostDTO.setHostName(host.getNickName());
        return new DetailPlaceResponse(
                id, address, title, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
                , file, hostDTO, review, scrap, hashtags, facilitys, dayOfWeeks, categoryName, isConfirmed
        );
    }

}