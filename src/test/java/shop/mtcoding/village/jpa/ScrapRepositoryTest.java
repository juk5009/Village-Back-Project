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
import shop.mtcoding.village.model.scrap.Scrap;
import shop.mtcoding.village.model.scrap.ScrapRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ScrapRepositoryTest {

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp(4L,4L,4);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Scrap> scraps = scrapRepository.findAll();
        Assertions.assertNotEquals(scraps.size(), 0);

        Scrap scrap = scraps.get(0);
        Assertions.assertEquals(scrap.getUserId(), 4L);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalScrap = this.scrapRepository.findById(4L);

        if(optionalScrap.isPresent()) {
            var result = optionalScrap.get();
            Assertions.assertEquals(result.getUserId(), 4L);

            var count = 4;
            result.setCount(count);
            Scrap merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getCount(), 4);
        } else {
            Assertions.assertNotNull(optionalScrap.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Scrap scrap = setUp(5L, 5L, 5);
        Optional<Scrap> findUser = this.scrapRepository.findById(scrap.getId());

        if(findUser.isPresent()) {
            var result = findUser.get();
            Assertions.assertEquals(result.getUserId(), 5L);
            entityManager.remove(scrap);
            Optional<Scrap> deleteUser = this.scrapRepository.findById(scrap.getId());
            if (deleteUser.isPresent()) {
                Assertions.assertNull(deleteUser.get());
            }
        } else {
            Assertions.assertNotNull(findUser.get());
        }
    }


    private Scrap setUp(Long userId, Long placeId, int count) {
        Scrap scrap = new Scrap();
        scrap.setUserId(userId);
        scrap.setPlaceId(placeId);
        scrap.setCount(count);
        return this.entityManager.persist(scrap);
    }
}
