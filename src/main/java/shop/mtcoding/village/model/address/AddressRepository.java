package shop.mtcoding.village.model.address;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface AddressRepository extends JpaRepository<Search,Long> {
}
