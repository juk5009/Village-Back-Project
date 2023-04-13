package shop.mtcoding.village.model.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.village.model.search.Search;
import shop.mtcoding.village.model.user.User;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query("select a from Address a where a.id = :id")
    Optional<Address> findByLongId(@Param("id") Long id);
}
