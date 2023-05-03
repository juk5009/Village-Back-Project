package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.review.Review;

import java.util.List;


@Getter
@Setter
public class DetailPlaceResponse {

    private File file;

    private Boolean isConfirmed;

    private Place place;

    private Review review;

    private List<Hashtag> hashtags;

    private List<FacilityInfo> facilitys;

    private List<Dates> dayOfWeeks;


}
