package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;

import java.util.List;

@ToString
@Getter
public class PlaceSaveResponse {
    private String title;

    private Address placeAddress;

    private String tel;

    private String startTime;

    private String endTime;

    private String placeIntroductionInfo;

    private Integer maxPeople;

    private Integer pricePerHour;

    private String notice;
//
//    private List<FileInfo> fileInfo;
//    @Null
//    private List<Review> review;


    public PlaceSaveResponse(String title, Address placeAddress, String tel, String startTime, String endTime, String placeIntroductionInfo, Integer maxPeople
            , Integer pricePerHour, String notice) {
        this.title = title;
        this.placeAddress = placeAddress;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.notice = notice;
    }

}
