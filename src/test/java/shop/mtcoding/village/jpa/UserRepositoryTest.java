package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.account.Account;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.chatRoom.ChatRoomRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE user_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
        setUp("love", "1234", "love@nate.com", "010-7474-1212", "USER","profile");
    }
    @Test
    @Transactional
    void selectAll() {
        List<User> users = userRepository.findAll();
        Assertions.assertNotEquals(users.size(), 0);

        User user = users.get(0);
        Assertions.assertEquals(user.getName(), "love");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalUser = this.userRepository.findById(4L);

        if(optionalUser.isPresent()) {
            var result = optionalUser.get();
            Assertions.assertEquals(result.getName(), "love");

            var profile = "profile55";
            result.setProfile(profile);
            User merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getProfile(), "profile55");
        } else {
            Assertions.assertNotNull(optionalUser.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        User user = setUp("love1", "12341", "love1@nate.com", "010-7474-12121", "USER","profile");
        Optional<User> findUser = this.userRepository.findById(user.getId());

        if(findUser.isPresent()) {
            var result = findUser.get();
            Assertions.assertEquals(result.getName(),"love1");
            entityManager.remove(user);
            Optional<User> deleteUser = this.userRepository.findById(user.getId());
            if (deleteUser.isPresent()) {
                Assertions.assertNull(deleteUser.get());
            }
        } else {
            Assertions.assertNotNull(findUser.get());
        }
    }


    public User setUp(String name, String password, String email, String tel, String role, String profile) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setTel(tel);
        user.setRole(role);
        user.setProfile(profile);
        return this.entityManager.persist(user);
    }
}
