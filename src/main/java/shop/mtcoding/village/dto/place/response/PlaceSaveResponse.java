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

    private Integer maxParking;

    private Integer pricePerHour;

    private String notice;

    private List<String> dayOfWeek;

    private List<String> hashtagName;

    private List<String> facilityName;

    private String categoryName;

    public PlaceSaveResponse(String title, Address placeAddress, String tel, String startTime, String endTime, String placeIntroductionInfo, Integer maxPeople
            ,Integer maxParking, Integer pricePerHour, String notice, List<String> dayOfWeek, List<String> hashtagName, List<String> facilityName, String categoryName) {
        this.title = title;
        this.placeAddress = placeAddress;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.notice = notice;
        this.dayOfWeek = dayOfWeek;
        this.hashtagName = hashtagName;
        this.facilityName = facilityName;
        this.categoryName = categoryName;
    }



}
