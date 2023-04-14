package shop.mtcoding.village.model.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
