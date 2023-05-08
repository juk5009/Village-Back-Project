package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.dto.host.HostDTO;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceAddress;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.scrap.Scrap;

import java.util.List;


@Getter
@Setter
public class DetailPlaceResponse {

    // Place  데이터
    private Long id;
    private String title;

    private PlaceAddress address;

    private String tel;

    private String startTime;

    private String endTime;

    private String placeIntroductionInfo;

    private Integer maxPeople;

    private Integer maxParking;

    private Integer pricePerHour;

    private String notice;

    private Boolean isConfirmed;

    // 다른 Entity 정보
    private List<File> file;

    private HostDTO host;

    private List<Review> review;

    private Integer scrap;

    private List<Hashtag> hashtags;

    private List<FacilityInfo> facilitys;

    private List<Dates> dayOfWeeks;

    private String categoryName;

    public DetailPlaceResponse(Long id, PlaceAddress address, String title, String tel, String startTime, String endTime, String placeIntroductionInfo, Integer maxPeople, Integer maxParking
            , Integer pricePerHour, String notice, List<File> file, HostDTO host, List<Review> review, Integer scrap, List<Hashtag> hashtags, List<FacilityInfo> facilitys, List<Dates> dayOfWeeks
            , String categoryName, Boolean isConfirmed) {
        this.id = id;
        this.address = address;
        this.title = title;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.notice = notice;
        this.file = file;
        this.host = host;
        this.review = review;
        this.scrap = scrap;
        this.hashtags = hashtags;
        this.facilitys = facilitys;
        this.dayOfWeeks = dayOfWeeks;
        this.categoryName = categoryName;
        this.isConfirmed = isConfirmed;
    }
}
