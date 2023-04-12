package shop.mtcoding.village.model.account;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.search.Search;

public interface AccountRepository extends JpaRepository<Address,Long> {
}
