package shop.mtcoding.village.model.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.review.Review;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {

    List<Hashtag> findByPlaceId(Long place_id);
}
