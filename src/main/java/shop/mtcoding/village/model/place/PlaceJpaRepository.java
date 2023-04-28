package shop.mtcoding.village.model.place;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceJpaRepository extends JpaRepository<Place, Long> {

//    @Query("SELECT p, d.dayOfWeekName FROM Place p LEFT JOIN Dates d ON p.id = d.placeId LEFT JOIN Hashtag h ON p.id = h.placeId WHERE p.id = :id")
//    Place findAllWithDate(@Param("id") Long id);







}
