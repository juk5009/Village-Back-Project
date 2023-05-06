package shop.mtcoding.village.model.review;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByPlaceId(Long place_id);
}
