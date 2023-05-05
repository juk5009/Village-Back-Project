package shop.mtcoding.village.model.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.village.model.place.PlaceAddress;

import java.util.Optional;

public interface PlaceAddressRepository extends JpaRepository<PlaceAddress,Long> {
    @Query("select a from PlaceAddress a where a.id = :id")
    Optional<Address> findByLongId(@Param("id") Long id);
}
