package shop.mtcoding.village.model.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{


    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    User findByEmailAndPassword(String email, String password);

    User findByName(String name);
}
