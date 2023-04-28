package shop.mtcoding.village.dto.place.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PlaceList {
    private Long id;
    private String title;
    private Integer maxPeople;
    private Integer maxParking;
    private Integer pricePerHour;
    private String address;
    private Integer starRating;
    private String hashtagName;
    private Long reviewCount;

    public PlaceList(BigInteger id, String title, Integer maxPeople, Integer maxParking, Integer pricePerHour, String address, Integer starRating, String hashtagName, BigInteger reviewCount) {
        this.id = id.longValue();
        this.title = title;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.address = address;
        this.starRating = starRating;
        this.hashtagName = hashtagName;
        this.reviewCount = reviewCount.longValue();
    }
}