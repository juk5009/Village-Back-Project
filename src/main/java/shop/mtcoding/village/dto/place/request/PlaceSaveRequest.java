package shop.mtcoding.village.dto.place.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.place.Place;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PlaceSaveRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "주소을 입력해주세요.")
    private String placeAddress;

    private String tel;

    @NotNull(message = "대여 가능 시작시간을 입력해주세요.")
    private LocalTime startTime;

    @NotNull(message = "대여 가능 마감시간을 입력해주세요.")
    private LocalTime endTime;

    @NotNull(message = "공간의 정보를 입력해주세요.")
    private String placeIntroductionInfo;

    @NotNull(message = "사용가능한 요일을 설정해주세요.")
    private List<String> dayOfWeek;

    private List<String> hashtag;

    private List<String> facilityInfo;


//    @Size(min = 1, message = "사진을 하나이상 등록해주세요.")
//    @NotNull(message = "사진을 하나이상 등록해주세요.")
//    private List<String> fileInfo;
//
//   @Null
//    private List<Review> review;
    @NotBlank(message = "유의사항에 대해 입력해주세요.")
    private String notice;

    @NotNull(message = "최대 인원수를 입력해주세요.")
    private Integer maxPeople;

    @NotNull(message = "시간당 금액을 입력해주세요.")
    private Integer pricePerHour;


    @NotNull(message = "카테고리를 등록해주세요.")
    private String category;


    public Place toEntity(){

        Address address = new Address();
        address.setRoadFullAddr(placeAddress);

        Category categoryName = new Category();
        categoryName.setName(category);

        Dates date = new Dates();
        String dayOfWeekAsString = String.join(",", dayOfWeek);
        date.setDayOfWeekName(dayOfWeekAsString);
        System.out.println("디버그 : " + date);

//        for (String element : dayOfWeek) {
//            String[] split = element.split(":");
//            String key = split[0];
//            String value = split[1];
//            System.out.println(key + " is " + value);
//            date.setDayOfWeekName(element);
//        }

        FacilityInfo facilityName = new FacilityInfo();
        String facilityAsString = String.join(",", facilityInfo);
        facilityName.setFacilityName(facilityAsString);

        Hashtag hashtagName = new Hashtag();
        String hashtagAsString = String.join(",", hashtag);
        hashtagName.setHashtagName(hashtagAsString);

//        FileInfo fileType = new FileInfo();
//        String fileInfoAsString = String.join(",", fileInfo);
//        fileType.setType(FileType.valueOf(fileInfoAsString));
//
//        Review reviewList = new Review();

        Place place = new Place();
        place.setTitle(title);
        place.setAddress(address);
        place.setTel(tel);
        place.setStartTime(startTime);
        place.setEndTime(endTime);
        place.setPlaceIntroductionInfo(placeIntroductionInfo);
        place.setDayOfWeek(date);
        place.setFacilityInfo(facilityName);
        place.setHashtag(hashtagName);
//        place.setFileInfo(fileType);
//        place.setReview((List<Review>) reviewList);
        place.setNotice(notice);
        place.setMaxPeople(maxPeople);
        place.setPricePerHour(pricePerHour);
        place.setCategory(categoryName);
        return new Place(title, address, tel, startTime, endTime, placeIntroductionInfo
                , date, hashtagName, facilityName
                , notice, maxPeople, categoryName, pricePerHour);
    }


}
