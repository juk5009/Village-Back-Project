package shop.mtcoding.village.model.search;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.hashtag.response.HashtagList;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<SearchList> searchPlacesByKeyword(String keyword) {
        String queryString =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, s.keyword, a.sgg_nm, r.star_rating, h.id as hashtag_id, h.hashtag_name, COUNT(r.id) as review_count " +
                        "FROM place_tb p " +
                        "LEFT JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN search_tb s ON s.place_id = p.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "WHERE p.title LIKE CONCAT('%', ?, '%') OR h.hashtag_name LIKE CONCAT('%', ?, '%') " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, s.keyword, a.sgg_nm, r.star_rating, h.id, h.hashtag_name";
        ;

        return jdbcTemplate.query(queryString, searchListResultSetExtractor(), keyword, keyword);
    }

    private ResultSetExtractor<List<SearchList>> searchListResultSetExtractor() {
        return rs -> {
            Map<Long, SearchList> searchMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                Integer maxPeople = rs.getInt("max_people");
                Integer maxParking = rs.getInt("max_parking");
                Integer pricePerHour = rs.getInt("price_per_hour");
                String searchKeyword = rs.getString("keyword");
                String address = rs.getString("sgg_nm");
                Integer starRating = rs.getInt("star_rating");
                Long reviewCount = rs.getLong("review_count");
                Long hashtagId = rs.getLong("hashtag_id");
                String hashtagName = rs.getString("hashtag_name");

                SearchList search = searchMap.computeIfAbsent(id, k -> {
                    SearchList s = new SearchList();
                    s.setHashtag(new ArrayList<>()); // hashtags 리스트 초기화
                    return s;
                });

                search.setId(id);
                search.setTitle(title);
                search.setMaxPeople(maxPeople);
                search.setMaxParking(maxParking);
                search.setPricePerHour(pricePerHour);
                search.setKeyword(searchKeyword);
                search.setAddress(address);
                search.setStarRating(starRating);
                search.setReviewCount(reviewCount);

                if (hashtagId != null) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(hashtagName);
                    search.getHashtag().add(hashtag);
                }
            }
            return new ArrayList<>(searchMap.values());
        };
    }

    public List<SearchOrderby> searchPlacesByPriceDescending() {
        String sqlPriceDesc =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.id, h.hashtag_name " +
                        "ORDER BY p.price_per_hour DESC";


        return jdbcTemplate.query(sqlPriceDesc, searchOrderResultSetExtractor());
    }

//



    private ResultSetExtractor<List<SearchOrderby>> searchOrderResultSetExtractor() {
        return rs -> {
            Map<Long, SearchOrderby> searchMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                SearchOrderby search = searchMap.getOrDefault(id, new SearchOrderby());
                search.setId(id);
                search.setTitle(rs.getString("title"));
                search.setMaxPeople(rs.getInt("max_people"));
                search.setMaxParking(rs.getInt("max_parking"));
                search.setPricePerHour(rs.getInt("price_per_hour"));
                search.setAddress(rs.getString("sgg_nm"));
                search.setStarRating(rs.getInt("star_rating"));
                search.setReviewCount(rs.getLong("review_count"));

                Long hashtagId = rs.getLong("hashtag_id");
                String hashtagName = rs.getString("hashtag_name");
                if (hashtagId != null) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(hashtagName);
                    search.getHashtags().add(hashtag);
                }

                searchMap.put(id, search); // searchMap 업데이트
            }
            List<SearchOrderby> searchList = new ArrayList<>(searchMap.values());
            Collections.sort(searchList, Comparator.comparing(SearchOrderby::getPricePerHour).reversed());
            return searchList;
        };
    }


}

//    public List<SearchOrderby> searchPlacesByPriceAscending() {
//
//        String sqlPriceAsc =
//                "SELECT p.id, p.title, p.max_people AS maxPeople, p.max_parking AS maxParking, p.price_per_hour AS pricePerHour, CONCAT(a.sido_nm, ' ', a.sgg_nm) AS address, COALESCE(AVG(r.star_rating), 0) AS starRating, COUNT(r.id) AS reviewCount, JSON_ARRAYAGG(JSON_OBJECT('id', h.id, 'hashtagName', h.hashtag_name)) AS hashtags " +
//                        "FROM place_tb p " +
//                        "LEFT JOIN address_tb a ON p.address_id = a.id " +
//                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
//                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
//                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sido_nm, a.sgg_nm " +
//                        "ORDER BY p.price_per_hour ASC";
//
//
//
//        return jdbcTemplate.query(sqlPriceAsc, searchOrderResultSetExtractor());
//    }


//    private static final String sqlPriceDesc =
//            "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
//                    "FROM place_tb p " +
//                    "INNER JOIN address_tb a ON p.address_id = a.id " +
//                    "LEFT JOIN review_tb r ON p.id = r.place_id " +
//                    "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
//                    "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name " +
//                    "ORDER BY p.price_per_hour DESC";
//
//    private static final String sqlPriceAsc =
//            "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
//                    "FROM place_tb p " +
//                    "INNER JOIN address_tb a ON p.address_id = a.id " +
//                    "LEFT JOIN review_tb r ON p.id = r.place_id " +
//                    "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
//                    "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name " +
//                    "ORDER BY p.price_per_hour ASC";
//
//    private static final String sqlStarRaingHigh =
//            "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
//                    "FROM place_tb p " +
//                    "INNER JOIN address_tb a ON p.address_id = a.id " +
//                    "LEFT JOIN review_tb r ON p.id = r.place_id " +
//                    "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
//                    "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name " +
//                    "ORDER BY r.star_rating DESC";


//    public List<SearchOrderby> searchPlacesByPriceDescending() {
//        Query query = em.createNativeQuery(sqlPriceDesc);
//
//        JpaResultMapper result = new JpaResultMapper();
//        List<SearchOrderby> searchPlaceByPriceDesc = result.list(query, SearchOrderby.class);
//
//        return searchPlaceByPriceDesc;
//    }
//
//    public List<SearchOrderby> searchPlacesByPriceAscending() {
//        Query query = em.createNativeQuery(sqlPriceAsc);
//
//        JpaResultMapper result = new JpaResultMapper();
//        List<SearchOrderby> searchPlaceByPriceAsc = result.list(query, SearchOrderby.class);
//
//        return searchPlaceByPriceAsc;
//    }
//
//    public List<SearchOrderby> searchPlaceByStarRaingDescending() {
//        Query query = em.createNativeQuery(sqlStarRaingHigh);
//
//        JpaResultMapper result = new JpaResultMapper();
//        List<SearchOrderby> searchPlaceByStarRaingDesc = result.list(query, SearchOrderby.class);
//
//        return searchPlaceByStarRaingDesc;
//    }

