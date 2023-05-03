package shop.mtcoding.village.model.facilityInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.review.Review;

import java.util.List;

public interface FacilityInfoRepository extends JpaRepository<FacilityInfo,Long> {

    List<FacilityInfo> findByPlaceId(Long place_id);
}
