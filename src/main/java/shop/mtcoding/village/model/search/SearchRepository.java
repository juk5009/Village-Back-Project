package shop.mtcoding.village.model.search;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.address.AddressList;
import shop.mtcoding.village.dto.file.response.FileList;
import shop.mtcoding.village.dto.hashtag.response.HashtagList;
import shop.mtcoding.village.dto.review.response.ReviewList;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.address.PlaceAddressRepository;
import shop.mtcoding.village.model.place.PlaceAddress;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PlaceAddressRepository addressRepository;

    public List<SearchList> searchPlacesByKeyword(String keyword) {
        String queryString =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, s.keyword, a.id as address_id, a.sigungu as sigungu, AVG(r.star_rating) as avg_star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name, f.id as file_id, f.file_url as file_url " +
                        "FROM place_tb p " +
                        "INNER JOIN place_address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "LEFT JOIN file_tb f ON p.id = f.place_id " +
                        "WHERE p.title LIKE CONCAT('%', ?, '%') OR h.hashtag_name LIKE CONCAT('%', ?, '%') " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id, a.sigungu, h.id, h.hashtag_name, f.id, f.file_url";





        return jdbcTemplate.query(queryString, searchListResultSetExtractor(), keyword, keyword);
    }

    private ResultSetExtractor<List<SearchList>> searchListResultSetExtractor() {
        return rs -> {
            Map<Long, SearchList> searchMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");

                SearchList search = searchMap.get(id);
                if (search == null) {
                    search = new SearchList();
                    search.setId(id);
                    search.setTitle(rs.getString("title"));
                    search.setMaxPeople(rs.getInt("max_people"));
                    search.setMaxParking(rs.getInt("max_parking"));
                    search.setPricePerHour(rs.getInt("price_per_hour"));
                    // search.setKeyword(rs.getString("keyword"));

                    Long addressId = rs.getLong("address_id");
                    if (addressId != null) {
                        PlaceAddress address = addressRepository.findById(addressId).orElse(null);
                        if (address != null) {
                            AddressList addressList = new AddressList();
                            addressList.setId(address.getId());
                            addressList.setSigungu(address.getSigungu());
                            search.setAddress(addressList);
                        }
                    }

                    ReviewList review = new ReviewList();
                    search.setReview(review);
                    search.setHashtags(new ArrayList<>());
                    search.setFileUrls(new ArrayList<>());

                    searchMap.put(id, search);
                }

                Double avgStarRating = rs.getDouble("avg_star_rating");
                if (avgStarRating != 0) {
                    search.getReview().setStarRating(avgStarRating);
                }
                Long reviewCount = rs.getLong("review_count");
                if (reviewCount != 0) {
                    search.getReview().setReviewCount(reviewCount);
                }

                Long hashtagId = rs.getLong("hashtag_id");
                if (hashtagId != 0 && !containsHashtag(search.getHashtags(), hashtagId)) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(rs.getString("hashtag_name"));
                    search.getHashtags().add(hashtag);
                }

                Long fileId = rs.getLong("file_id");
                if (fileId != 0 && !containsFileUrl(search.getFileUrls(), fileId)) {
                    FileList file = new FileList();
                    file.setId(fileId);
                    file.setFileUrl(rs.getString("file_url"));
                    search.getFileUrls().add(file);
                }
            }
            return new ArrayList<>(searchMap.values());
        };
    }




    public List<SearchOrderby> searchPlacesByPriceDescending() {
        String sqlPriceDesc =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id as address_id, a.sigungu as sigungu, AVG(r.star_rating) as avg_star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name, f.id as file_id, f.file_url as file_url " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "LEFT JOIN file_tb f ON p.id = f.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id, a.sigungu, h.id, h.hashtag_name, f.id, f.file_url "+
                        "ORDER BY p.price_per_hour DESC";


        return jdbcTemplate.query(sqlPriceDesc, searchOrderByResultSetExtractor(true));
    }

    public List<SearchOrderby> searchPlacesByPriceAscending() {
        String sqlPriceAsc =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id as address_id, a.sigungu as sigungu, AVG(r.star_rating) as avg_star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name, f.id as file_id, f.file_url as file_url " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "LEFT JOIN file_tb f ON p.id = f.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id, a.sigungu, h.id, h.hashtag_name, f.id, f.file_url "+
                        "ORDER BY p.price_per_hour ASC";


        return jdbcTemplate.query(sqlPriceAsc, searchOrderByResultSetExtractor(false));
    }

    public List<SearchOrderby> searchPlaceByStarRaingDescending() {
        String sqlStarRaingHigh =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id as address_id, a.sigungu as sigungu, AVG(r.star_rating) as avg_star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name, f.id as file_id, f.file_url as file_url " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "LEFT JOIN file_tb f ON p.id = f.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id, a.sigungu, h.id, h.hashtag_name, f.id, f.file_url "+
                        "ORDER BY r.star_rating DESC";


        return jdbcTemplate.query(sqlStarRaingHigh, searchOrderByStarRatingResultSetExtractor());
    }

    private ResultSetExtractor<List<SearchOrderby>> searchOrderByResultSetExtractor(boolean isOrderby) {
        return rs -> {
            Map<Long, SearchOrderby> searchMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");

                SearchOrderby search = searchMap.get(id);
                if (search == null) {
                    search = new SearchOrderby();
                    search.setId(id);
                    search.setTitle(rs.getString("title"));
                    search.setMaxPeople(rs.getInt("max_people"));
                    search.setMaxParking(rs.getInt("max_parking"));
                    search.setPricePerHour(rs.getInt("price_per_hour"));


                    Long addressId = rs.getLong("address_id");
                    if (addressId != null) {
                        PlaceAddress address = addressRepository.findById(addressId).orElse(null);
                        if (address != null) {
                            AddressList addressList = new AddressList();
                            addressList.setId(address.getId());
                            addressList.setSigungu(address.getSigungu());
                            search.setAddress(addressList);
                        }
                    }

                    ReviewList review = new ReviewList();
                    search.setReview(review);
                    search.setHashtags(new ArrayList<>());
                    search.setFileUrls(new ArrayList<>());

                    searchMap.put(id, search);
                }

                Double avgStarRating = rs.getDouble("avg_star_rating");
                if (avgStarRating != 0) {
                    search.getReview().setStarRating(avgStarRating);
                }
                Long reviewCount = rs.getLong("review_count");
                if (reviewCount != 0) {
                    search.getReview().setReviewCount(reviewCount);
                }

                Long hashtagId = rs.getLong("hashtag_id");
                if (hashtagId != 0 && !containsHashtag(search.getHashtags(), hashtagId)) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(rs.getString("hashtag_name"));
                    search.getHashtags().add(hashtag);
                }

                Long fileId = rs.getLong("file_id");
                if (fileId != 0 && !containsFileUrl(search.getFileUrls(), fileId)) {
                    FileList file = new FileList();
                    file.setId(fileId);
                    file.setFileUrl(rs.getString("file_url"));
                    search.getFileUrls().add(file);
                }
            }
            List<SearchOrderby> searchList = new ArrayList<>(searchMap.values());
            if (isOrderby) {
                Collections.sort(searchList, Comparator.comparing(SearchOrderby::getPricePerHour).reversed());
            } else {
                Collections.sort(searchList, Comparator.comparing(SearchOrderby::getPricePerHour));
            }
            return searchList;
        };
    }

    private ResultSetExtractor<List<SearchOrderby>> searchOrderByStarRatingResultSetExtractor() {
        return rs -> {
            Map<Long, SearchOrderby> searchMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");

                SearchOrderby search = searchMap.get(id);
                if (search == null) {
                    search = new SearchOrderby();
                    search.setId(id);
                    search.setTitle(rs.getString("title"));
                    search.setMaxPeople(rs.getInt("max_people"));
                    search.setMaxParking(rs.getInt("max_parking"));
                    search.setPricePerHour(rs.getInt("price_per_hour"));


                    Long addressId = rs.getLong("address_id");
                    if (addressId != null) {
                        PlaceAddress address = addressRepository.findById(addressId).orElse(null);
                        if (address != null) {
                            AddressList addressList = new AddressList();
                            addressList.setId(address.getId());
                            addressList.setSigungu(address.getSigungu());
                            search.setAddress(addressList);
                        }
                    }

                    ReviewList review = new ReviewList();
                    search.setReview(review);
                    search.setHashtags(new ArrayList<>());
                    search.setFileUrls(new ArrayList<>());

                    searchMap.put(id, search);
                }

                Double avgStarRating = rs.getDouble("avg_star_rating");
                if (avgStarRating != 0) {
                    search.getReview().setStarRating(avgStarRating);
                }
                Long reviewCount = rs.getLong("review_count");
                if (reviewCount != 0) {
                    search.getReview().setReviewCount(reviewCount);
                }

                Long hashtagId = rs.getLong("hashtag_id");
                if (hashtagId != 0 && !containsHashtag(search.getHashtags(), hashtagId)) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(rs.getString("hashtag_name"));
                    search.getHashtags().add(hashtag);
                }

                Long fileId = rs.getLong("file_id");
                if (fileId != 0 && !containsFileUrl(search.getFileUrls(), fileId)) {
                    FileList file = new FileList();
                    file.setId(fileId);
                    file.setFileUrl(rs.getString("file_url"));
                    search.getFileUrls().add(file);
                }
            }
            List<SearchOrderby> searchList = new ArrayList<>(searchMap.values());
            Collections.sort(searchList, Comparator.comparing(search -> search.getReview().getStarRating(), Comparator.reverseOrder()));
            return searchList;
        };
    }


    //중복 확인 코드
    private boolean containsHashtag(List<HashtagList> hashtags, Long hashtagId) {
        return hashtags.stream().anyMatch(hashtag -> hashtag.getId().equals(hashtagId));
    }


    private boolean containsFileUrl(List<FileList> fileUrls, Long fileId) {
        return fileUrls.stream().anyMatch(file -> file.getId().equals(fileId));
    }


}
