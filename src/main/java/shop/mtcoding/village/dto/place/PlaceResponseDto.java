package shop.mtcoding.village.dto.place;

import shop.mtcoding.village.model.date.Dates;

import java.time.LocalDateTime;
import java.util.List;

public class PlaceResponseDto {
    private String title;

    private String placeAddress;

    private String tel;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String placeIntroductionInfo;

    private List<Dates> dayOfWeek;


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

    private String notice;

    private Integer maxPeople;

    private Integer pricePerHour;

    private String category;
}
