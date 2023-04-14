package shop.mtcoding.village.model.account;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.search.Search;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findById(Integer id);
}

