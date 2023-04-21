//package shop.mtcoding.village.dto.search;
//
//import lombok.Getter;
//import lombok.Setter;
//import shop.mtcoding.village.model.address.Address;
//import shop.mtcoding.village.model.hashtag.Hashtag;
//import shop.mtcoding.village.model.place.Place;
//import shop.mtcoding.village.model.review.Review;
//import shop.mtcoding.village.model.search.Search;
//
//import java.util.List;
//
//@Setter
//@Getter
//public class SearchResponse {
//    private Long id;
//    private String title;
//    private String keyword;
//    private Integer maxPeople;
//    private Integer maxParking;
//    private String address;
//    private String hashtagName; //hashtag
//    private Integer pricePerHour; //place
//    private Integer starRating; //review
//
//
//    public SearchResponse toResponse() {
//        Search search = new Search();
//        Place place = new Place();
//        Address adress = new Address();
//        Hashtag hashtag = new Hashtag();
//        Review review = new Review();
//        this.id = search.getId();
//        this.title = place.getTitle();
//        this.keyword = search.getKeyword();
//        this.maxPeople = place.getMaxPeople();
//        this.maxParking = place.getMaxParking();
//        this.address = adress.getSggNm();
////        this.hashtagName = hashtag.getHashtagName();
//        this.pricePerHour = place.getPricePerHour();
//        this.starRating = review.getStarRating();
//    }
//
//}
//
//
