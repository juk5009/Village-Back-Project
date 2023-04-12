package shop.mtcoding.village.model.place;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface PlaceRepository extends JpaRepository<Place,Long> {
}
