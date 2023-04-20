package shop.mtcoding.village.model.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.mtcoding.village.model.search.Search;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
