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
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.address.AddressRepository;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class PlaceRepository {

    private final JdbcTemplate jdbcTemplate;

    private final AddressRepository addressRepository;

    public List<PlaceList> PlaceList() {
        String queryString =

                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id as address_id, a.sigungu as sigungu, AVG(r.star_rating) as avg_star_rating, COUNT(r.id) as review_count, h.id as hashtag_id, h.hashtag_name, f.id as file_id, f.file_url as file_url " +
                        "FROM place_tb p " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "LEFT JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "LEFT JOIN search_tb s ON p.id = s.place_id " +
                        "LEFT JOIN file_tb f ON p.id = f.place_id " +
                        "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.id, a.sigungu, h.id, h.hashtag_name, f.id, f.file_url";


        return jdbcTemplate.query(queryString, placeListResultSetExtractor());
    }


    private ResultSetExtractor<List<PlaceList>> placeListResultSetExtractor() {
        return rs -> {
            Map<Long, PlaceList> placeMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("id");

                PlaceList place = placeMap.get(id);
                if (place == null) {
                    place = new PlaceList();
                    place.setId(id);
                    place.setTitle(rs.getString("title"));
                    place.setMaxPeople(rs.getInt("max_people"));
                    place.setMaxParking(rs.getInt("max_parking"));
                    place.setPricePerHour(rs.getInt("price_per_hour"));

                    Long addressId = rs.getLong("address_id");
                    if (addressId != null) {
                        Address address = addressRepository.findById(addressId).orElse(null);
                        if (address != null) {
                            AddressList addressList = new AddressList();
                            addressList.setId(address.getId());
                            addressList.setSigungu(address.getSigungu());
                            place.setAddress(addressList);
                        }
                    }

                    ReviewList review = new ReviewList();
                    place.setReview(review);
                    place.setHashtags(new ArrayList<>());
                    place.setFileUrls(new ArrayList<>());

                    placeMap.put(id, place);
                }

                Double avgStarRating = rs.getDouble("avg_star_rating");
                if (avgStarRating != 0) {
                    place.getReview().setStarRating(avgStarRating);
                }
                Long reviewCount = rs.getLong("review_count");
                if (reviewCount != 0) {
                    place.getReview().setReviewCount(reviewCount);
                }

                Long hashtagId = rs.getLong("hashtag_id");
                if (hashtagId != 0 && !containsHashtag(place.getHashtags(), hashtagId)) {
                    HashtagList hashtag = new HashtagList();
                    hashtag.setId(hashtagId);
                    hashtag.setHashtagName(rs.getString("hashtag_name"));
                    place.getHashtags().add(hashtag);
                }

                Long fileId = rs.getLong("file_id");
                if (fileId != 0 && !containsFileUrl(place.getFileUrls(), fileId)) {
                    FileList file = new FileList();
                    file.setId(fileId);
                    file.setFileUrl(rs.getString("file_url"));
                    place.getFileUrls().add(file);
                }
            }
            return new ArrayList<>(placeMap.values());
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