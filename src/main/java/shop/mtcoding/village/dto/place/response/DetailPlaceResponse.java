package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.scrap.Scrap;

import java.util.List;


@Getter
@Setter
public class DetailPlaceResponse {

    // Place  데이터
    private String title;

    private AddressDTO placeAddress;

    private String tel;

    private String startTime;

    private String endTime;

    private String placeIntroductionInfo;

    private Integer maxPeople;

    private Integer maxParking;

    private Integer pricePerHour;

    private String notice;

    // 다른 Entity 정보
    private File file;

    private Host host;

    private Review review;

    private Scrap scrap;

    private List<Hashtag> hashtags;

    private List<FacilityInfo> facilitys;

    private List<Dates> dayOfWeeks;

    public DetailPlaceResponse(String title, String tel, String startTime, String endTime, String placeIntroductionInfo, Integer maxPeople, Integer maxParking
            , Integer pricePerHour, String notice, File file, Host host, Review review, Scrap scrap, List<Hashtag> hashtags, List<FacilityInfo> facilitys, List<Dates> dayOfWeeks) {
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
    }
}
