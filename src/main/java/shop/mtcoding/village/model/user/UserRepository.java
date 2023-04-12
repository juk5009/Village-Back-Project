package shop.mtcoding.village.model.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{

    // 복사한거임 수정필요
    @Query("select u from User u where u.name = :name")
    Optional<User> findbyName(@Param("name") String name);
}
