package shop.mtcoding.village.model.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.village.model.review.Review;

import java.util.List;

public interface DateRepository extends JpaRepository<Dates,Long> {

    List<Dates> findByPlaceId(Long place_id);

//    @Query("select d from Dates d where d.placeId = :placeId")
//    List<Dates> findByPlaceId(@Param("placeId") Long placeId);
}
