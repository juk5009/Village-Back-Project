package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
public class PlaceSaveResponse {
    private String title;

    private Address placeAddress;

    private String tel;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String placeIntroductionInfo;

    private List<Dates> dayOfWeek;

    private Integer maxPeople;

    private Integer pricePerHour;

    private Category category;

    private String notice;

//    @Null
//    private List<FacilityInfo> facilityInfo;
//
//    @Null
//    private List<Hashtag> hashtag;
//
//    @Size(min = 1, message = "사진을 하나이상 등록해주세요.")
//    private List<FileInfo> fileInfo;
//    @Null
//    private List<Review> review;


    public PlaceSaveResponse(String title, Address roadFullAddr, String tel, LocalDateTime startTime, LocalDateTime endTime, String placeIntroductionInfo, List<Dates> dayOfWeekName
            , String notice, Integer maxPeople, Integer pricePerHour, Category name) {
        this.title = title;
        this.placeAddress = roadFullAddr;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.dayOfWeek = dayOfWeekName;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.category = name;
    }
}
