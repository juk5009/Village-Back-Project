package shop.mtcoding.village.model.place;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.address.AddressList;
import shop.mtcoding.village.dto.file.response.FileList;
import shop.mtcoding.village.dto.hashtag.response.HashtagList;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.dto.review.response.ReviewList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class PlaceRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PlaceList> PlaceList() {
        String queryString =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id as address_id, a.sigungu as sigungu, r.star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name, f.id as file_id, f.file_url as file_url " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "LEFT JOIN file_tb f ON p.id = f.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id, a.sigungu, r.star_rating, h.id, h.hashtag_name, f.id, f.file_url";

        return jdbcTemplate.query(queryString, placeListResultSetExtractor());
    }

    private ResultSetExtractor<List<PlaceList>> placeListResultSetExtractor() {
        return rs -> {
            Map<Long, PlaceList> placeMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                Integer maxPeople = rs.getInt("max_people");
                Integer maxParking = rs.getInt("max_parking");
                Integer pricePerHour = rs.getInt("price_per_hour");
                Long addressId = rs.getLong("address_id");
                String sigungu = rs.getString("sigungu");
                Integer starRating = rs.getInt("star_rating");
                Long reviewCount = rs.getLong("review_count");
                Long hashtagId = rs.getLong("hashtag_id");
                String hashtagName = rs.getString("hashtag_name");
                Long fileId = rs.getLong("file_id");
                String fileUrl = rs.getString("file_url");

                PlaceList place = placeMap.computeIfAbsent(id, k -> {
                    PlaceList p = new PlaceList();
                    p.setAddress(new ArrayList<>());
                    p.setReview(new ArrayList<>());
                    p.setHashtags(new ArrayList<>()); // hashtags 리스트 초기화
                    p.setFileUrls(new ArrayList<>()); // files 리스트 초기화
                    return p;
                });

                place.setId(id);
                place.setTitle(title);
                place.setMaxPeople(maxPeople);
                place.setMaxParking(maxParking);
                place.setPricePerHour(pricePerHour);

    private static final String sql = "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
            "FROM place_tb p " +
            "INNER JOIN address_tb a ON p.address_id = a.id " +
            "LEFT JOIN review_tb r ON p.id = r.place_id " +
            "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
            "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name ";

                if (addressId != null) {
                    AddressList address = new AddressList();
                    address.setId(addressId);
                    address.setSigungu(sigungu);
                    place.getAddress().add(address);
                }

                ReviewList review = new ReviewList();
                review.setStarRating(starRating);
                review.setReviewCount(reviewCount);
                place.getReview().add(review);

                if (hashtagId != null) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(hashtagName);
                    place.getHashtags().add(hashtag);
                }

                if (fileId != null) {
                    FileList file = new FileList();
                    file.setId(fileId);
                    file.setFileUrl(fileUrl);
                    place.getFileUrls().add(file);
                }
            }
            return new ArrayList<>(placeMap.values());
        };
    }
}
