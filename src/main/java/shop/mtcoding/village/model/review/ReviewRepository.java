package shop.mtcoding.village.model.review;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review findByPlaceId(Long place_id);
}
