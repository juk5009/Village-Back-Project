package shop.mtcoding.village.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<String> hashtagName;

    public SearchList(Long id, String title, Integer maxPeople, Integer maxParking, Integer pricePerHour, String Keyword, String address, Integer starRating, List<String> hashtagName) {
        this.id = id;
        this.title = title;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.keyword = Keyword;
        this.address = address;
        this.starRating = starRating;
        this.hashtagName = hashtagName;
    }
}






