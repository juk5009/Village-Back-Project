package shop.mtcoding.village.dto.place;

import lombok.Getter;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.place.Place;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PlaceSaveDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "주소을 입력해주세요.")
    private String placeAddress;   // Address 타입으로 변경 해야함

    private String tel;

    @NotNull(message = "대여 가능 시작시간을 입력해주세요.")
    private LocalDateTime startTime;  // 타입 프론트랑 상의 해서 바꿀것

    @NotNull(message = "대여 가능 마감시간을 입력해주세요.")
    private LocalDateTime endTime;    // 타입 프론트랑 상의 해서 바꿀것

    @NotNull(message = "공간의 정보를 입력해주세요.")
    private String placeIntroductionInfo;

    @NotNull(message = "사용가능한 요일을 설정해주세요.")
    private List<Dates> dayOfWeek;    // List<Date> 타입으로 변경 해야함


//    @Null
//    private List<FacilityInfo> facilityInfo;  // List<FacilityInfo> 타입으로 변경 해야함
//
//    @Null
//    private List<Hashtag> hashtag;  // List<Hashtag> 타입으로 변경 해야함
//
//    @Size(min = 1, message = "사진을 하나이상 등록해주세요.")
//    private List<FileInfo> fileInfo;   //  List<FileInfo>
//
//    @Null
//    private List<Review> review;  // List<Review> 타입으로 변경 해야함
    @NotBlank(message = "유의사항에 대해 입력해주세요.")
    private String notice;

    @NotNull(message = "최대 인원수를 입력해주세요.")
    private Integer maxPeople;

    @NotNull(message = "시간당 금액을 입력해주세요.")
    private Integer pricePerHour;


    @NotNull(message = "카테고리를 등록해주세요.")
    private String category; // Category 타입으로 변경 해야함


    public PlaceSaveDto(String title, String placeAddress, String tel, LocalDateTime startTime, LocalDateTime endTime, String placeIntroductionInfo
            , String notice, Integer maxPeople, Integer pricePerHour, String category,
                        List<Dates> dayOfWeek) {
        this.title = title;
        this.placeAddress = placeAddress;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.dayOfWeek = dayOfWeek;
//        this.facilityInfo = facilityInfo;
//        this.hashtag = hashtag;
//        this.fileInfo = fileInfo;
//        this.review = review;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.pricePerHour = pricePerHour;
        this.category = category;
    }

    public Place toEntity() {

        Address address = new Address();
        address.setRoadFullAddr(placeAddress);

        Category categoryName = new Category();
        categoryName.setName(category);

//        Date date = new Date();
//        date.setDayOfWeekName(dayOfWeek);

//        FacilityInfo facilityName = new FacilityInfo();
//        facilityName.setFacilityName(facilityInfo.toString());

//        Hashtag hashtagName = new Hashtag();
//        hashtagName.setHashtagName(hashtagName.toString());
//
//        FileInfo fileType = new FileInfo();
//        fileType.setType(FileType.Image);

//        Review reviewList = new Review().builder().build();

        Place place = new Place();
        place.setTitle(title);
        place.setAddress(address);
        place.setTel(tel);
        place.setStartTime(startTime);
        place.setEndTime(endTime);
        place.setPlaceIntroductionInfo(placeIntroductionInfo);
//        place.setDayOfWeek(date);
//        place.setFacilityInfo((List<FacilityInfo>) facilityName);
//        place.setHashtag((List<Hashtag>) hashtagName);
//        place.setFileInfo((List<FileInfo>) fileType);
//        place.setReview((List<Review>) reviewList);
        place.setNotice(notice);
        place.setMaxPeople(maxPeople);
        place.setPricePerHour(pricePerHour);
        place.setCategory(categoryName);
        return new Place(title, address, tel, startTime, endTime, placeIntroductionInfo
//                ,date
                , notice, maxPeople, categoryName, pricePerHour);
    }



}
