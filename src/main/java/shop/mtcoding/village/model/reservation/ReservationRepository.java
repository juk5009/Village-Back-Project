package shop.mtcoding.village.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
