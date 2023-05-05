package shop.mtcoding.village.model.fcm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FcmRepository extends JpaRepository<Fcm, Long> {

    Optional<Fcm> findByTargetToken(String targetToken);

    Fcm findByUserId(Long user_id);
}
