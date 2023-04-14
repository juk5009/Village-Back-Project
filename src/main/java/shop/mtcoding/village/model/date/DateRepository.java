package shop.mtcoding.village.model.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DateRepository extends JpaRepository<Dates,Long> {

    @Query("select d from Dates d where d.placeId = :placeId")
    List<Dates> findByPlaceId(@Param("placeId") Long placeId);
}
