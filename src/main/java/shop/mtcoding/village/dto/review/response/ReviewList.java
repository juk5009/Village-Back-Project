package shop.mtcoding.village.dto.review.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewList {
    private Double starRating;
    private Long reviewCount;
}
