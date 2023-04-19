package shop.mtcoding.village.dto.place.request;

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
public class PlaceUpdateRequest {

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

    @NotBlank(message = "유의사항에 대해 입력해주세요.")
    private String notice;

    @NotNull(message = "최대 인원수를 입력해주세요.")
    private Integer maxPeople;

    @NotNull(message = "시간당 금액을 입력해주세요.")
    private Integer pricePerHour;

    // 다른 엔티티들

    @NotNull(message = "사용가능한 요일을 설정해주세요.")
    private List<String> dayOfWeek;

    private List<String> hashtag;

    private List<String> facilityInfo;

    @NotNull(message = "카테고리를 등록해주세요.")
    private String category;

    public Place toEntity() {

        Address address = new Address();
        address.setRoadFullAddr(placeAddress);

        Category categoryName = new Category();
        categoryName.setCategoryName(category);

        Dates date = new Dates();
        List<String> dates = new ArrayList<>();
        String[] dayOfWeeks = {""};
        for (String dayOfWeek : dayOfWeeks) {
            dates.add(dayOfWeek);
        }
        date.setDayOfWeekName(dayOfWeek);

        FacilityInfo facilityName = new FacilityInfo();
        List<String> facility = new ArrayList<>();
        String[] facilityNames = {""};
        for (String facilityInfo : facilityNames) {
            facility.add(facilityInfo);
        }
        facilityName.setFacilityName(facilityInfo);

        Hashtag hashtagName = new Hashtag();
        List<String> hashtags = new ArrayList<>();
        String[] hashtagNames = {""};
        for (String hashtag : hashtagNames) {
            hashtags.add(hashtag);
        }
        hashtagName.setHashtagName(hashtag);

        Place place = new Place();
        place.setTitle(title);
        place.setAddress(address);
        place.setTel(tel);
        place.setStartTime(startTime);
        place.setEndTime(endTime);
        place.setPlaceIntroductionInfo(placeIntroductionInfo);
        place.setNotice(notice);
        place.setMaxPeople(maxPeople);
        place.setPricePerHour(pricePerHour);
        return new Place(
                title, address, tel, startTime, endTime, placeIntroductionInfo, notice, maxPeople, pricePerHour
        );

    }

}
