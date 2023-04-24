package shop.mtcoding.village.model.search;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.search.SearchList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<SearchList> searchPlacesByKeyword(String keyword) {
        String queryString =
                "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, s.keyword, a.sgg_nm, r.star_rating, h.hashtag_name " +
                        "FROM search_tb s " +
                        "INNER JOIN place_tb p ON s.place_id = p.id " +
                        "INNER JOIN address_tb a ON p.address_id = a.id " +
                        "INNER JOIN review_tb r ON p.id = r.place_id " +
                        "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
                        "WHERE p.title LIKE CONCAT('%', ?, '%') OR h.hashtag_name LIKE CONCAT('%', ?, '%')";

        return jdbcTemplate.query(queryString, searchListResultSetExtractor(), keyword, keyword);
    }

    private ResultSetExtractor<List<SearchList>> searchListResultSetExtractor() {
        return new ResultSetExtractor<List<SearchList>>() {
            @Override
            public List<SearchList> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<SearchList> searchList = new ArrayList<>();
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    Integer maxPeople = rs.getInt("max_people");
                    Integer maxParking = rs.getInt("max_parking");
                    Integer pricePerHour = rs.getInt("price_per_hour");
                    String searchKeyword = rs.getString("keyword");
                    String address = rs.getString("sgg_nm");
                    Integer starRating = rs.getInt("star_rating");
                    String hashtagName = rs.getString("hashtag_name");

                    // create a list for hashtags
                    List<String> hashtagList = getHashtagList(hashtagName);

                    // create a new search object and add it to the list
                    SearchList search = new SearchList(id, title, maxPeople, maxParking, pricePerHour, searchKeyword, address, starRating, hashtagList);
                    searchList.add(search);
                }
                return searchList;
            }
        };
    }

    private List<String> getHashtagList(String hashtagName) {
        List<String> hashtagList = new ArrayList<>();
        if (hashtagName != null) {
            hashtagList.add(hashtagName);
        }
        return hashtagList;
    }
}
