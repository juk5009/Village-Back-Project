package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;

import java.util.List;

@Getter
public class PlaceUpdateResponse {
    private String title;

    private Address placeAddress;

    private String tel;

    private String startTime;

    private String endTime;

    private String placeIntroductionInfo;

    private List<Dates> dayOfWeek;

    private Integer maxPeople;

    private Integer pricePerHour;

    private Category category;

    private String notice;

    private List<FacilityInfo> facilityInfo;

    private List<Hashtag> hashtag;
//
//    private List<FileInfo> fileInfo;
//    @Null
//    private List<Review> review;


    public PlaceUpdateResponse(String title, Address roadFullAddr, String tel, String startTime, String endTime, String placeIntroductionInfo, List<Dates> dayOfWeekName
            , List<Hashtag> hashtag, List<FacilityInfo> facilityInfo, String notice, Integer maxPeople, Integer pricePerHour, Category name) {
        this.title = title;
        this.placeAddress = roadFullAddr;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.dayOfWeek = dayOfWeekName;
        this.hashtag = hashtag;
        this.facilityInfo = facilityInfo;
//        this.fileInfo = fileInfo;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.category = name;
    }
}
