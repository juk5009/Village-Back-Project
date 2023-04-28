package shop.mtcoding.village.model.place;


import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.dto.place.response.PlaceList;
import shop.mtcoding.village.dto.search.SearchOrderby;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PlaceRepository {

    private final EntityManager em;

    private static final String sql = "SELECT p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name, COUNT(r.id) as review_count " +
            "FROM place_tb p " +
            "INNER JOIN address_tb a ON p.address_id = a.id " +
            "LEFT JOIN review_tb r ON p.id = r.place_id " +
            "LEFT JOIN hashtag_tb h ON p.id = h.place_id " +
            "GROUP BY p.id, p.title, p.max_people, p.max_parking, p.price_per_hour, a.sgg_nm, r.star_rating, h.hashtag_name ";

    public List<PlaceList> PlaceList() {
        Query query = em.createNativeQuery(sql);

        JpaResultMapper result = new JpaResultMapper();
        List<PlaceList> placeList = result.list(query, PlaceList.class);

        return placeList;
    }

}
