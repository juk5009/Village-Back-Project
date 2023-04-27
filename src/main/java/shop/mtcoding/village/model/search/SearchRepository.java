package shop.mtcoding.village.model.search;

import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SearchRepository {

    private final EntityManager em;

    private static final String sql = "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, s.keyword, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
            "FROM search_tb s " +
            "INNER JOIN place_tb p ON s.place_id = p.id " +
            "INNER JOIN address_tb a ON p.address_id = a.id " +
            "LEFT JOIN review_tb r ON p.id = r.place_id " +
            "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
            "WHERE p.title LIKE CONCAT('%', :keyword, '%') OR h.hashtag_name LIKE CONCAT('%', :keyword, '%') " +
            "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, s.keyword, a.sgg_nm, r.star_rating, h.hashtag_name";

    private static final String sqlPriceDesc = "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
            "FROM place_tb p " +
            "INNER JOIN address_tb a ON p.address_id = a.id " +
            "LEFT JOIN review_tb r ON p.id = r.place_id " +
            "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
            "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name " +
            "ORDER BY p.price_per_hour DESC";

    private static final String sqlPriceAsc = "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
            "FROM place_tb p " +
            "INNER JOIN address_tb a ON p.address_id = a.id " +
            "LEFT JOIN review_tb r ON p.id = r.place_id " +
            "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
            "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name " +
            "ORDER BY p.price_per_hour ASC";

    public List<SearchList> searchPlacesByKeyword(String keyword) {
        Query query = em.createNativeQuery(sql);
        query.setParameter("keyword", keyword);

        JpaResultMapper result = new JpaResultMapper();
        List<SearchList> searchList = result.list(query, SearchList.class);

        return searchList;
    }

    public List<SearchOrderby> searchPlacesByPriceDescending() {
        Query query = em.createNativeQuery(sqlPriceDesc);

        JpaResultMapper result = new JpaResultMapper();
        List<SearchOrderby> searchPlaceByPriceDesc = result.list(query, SearchOrderby.class);

        return searchPlaceByPriceDesc;
    }

    public List<SearchOrderby> searchPlacesByPriceAscending() {
        Query query = em.createNativeQuery(sqlPriceAsc);

        JpaResultMapper result = new JpaResultMapper();
        List<SearchOrderby> searchPlaceByPriceAsc = result.list(query, SearchOrderby.class);

        return searchPlaceByPriceAsc;
    }
}
