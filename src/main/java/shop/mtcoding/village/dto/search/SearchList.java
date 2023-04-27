package shop.mtcoding.village.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
public class SearchList {
    private Long id;
    private String title;
    private Integer maxPeople;
    private Integer maxParking;
    private Integer pricePerHour;
    private String keyword;
    private String address;
    private Integer starRating;
    private String hashtagName;
    private Long reviewCount;

    public SearchList(BigInteger id, String title, Integer maxPeople, Integer maxParking, Integer pricePerHour, String keyword, String address, Integer starRating, String hashtagName, BigInteger reviewCount) {
        this.id = id.longValue();
        this.title = title;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.keyword = keyword;
        this.address = address;
        this.starRating = starRating;
        this.hashtagName = hashtagName;
        this.reviewCount = reviewCount.longValue();
    }
}
