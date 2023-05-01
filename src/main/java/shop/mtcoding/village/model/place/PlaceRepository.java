package shop.mtcoding.village.model.place;


import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.hashtag.response.HashtagList;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.id, h.hashtag_name";

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
                String address = rs.getString("sgg_nm");
                Integer starRating = rs.getInt("star_rating");
                Long reviewCount = rs.getLong("review_count");
                Long hashtagId = rs.getLong("hashtag_id");
                String hashtagName = rs.getString("hashtag_name");

                PlaceList place = placeMap.computeIfAbsent(id, k -> {
                    PlaceList p = new PlaceList();
                    p.setHashtags(new ArrayList<>()); // hashtags 리스트 초기화
                    return p;
                });

                place.setId(id);
                place.setTitle(title);
                place.setMaxPeople(maxPeople);
                place.setMaxParking(maxParking);
                place.setPricePerHour(pricePerHour);
                place.setAddress(address);
                place.setStarRating(starRating);
                place.setReviewCount(reviewCount);

                if (hashtagId != null) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(hashtagName);
                    place.getHashtags().add(hashtag);
                }
            }
            return new ArrayList<>(placeMap.values());
        };
    }
}
