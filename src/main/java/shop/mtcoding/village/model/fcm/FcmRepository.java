package shop.mtcoding.village.model.fcm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmRepository extends JpaRepository<Fcm, Long> {

    Fcm findByTargetToken(String targetToken);

    Fcm findByUserId(Long user_id);
}
