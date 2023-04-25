package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.scrap.Scrap;
import shop.mtcoding.village.model.scrap.ScrapRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("스크랩 JPA 테스트")
public class ScrapRepositoryTest {

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;


    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE scrap_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();

//        setUpByScrap(4);
    }

    @Test
    @Transactional
    @DisplayName("스크랩 조회 테스트")
    void selectAll() {
        List<Scrap> scraps = scrapRepository.findAll();
        Assertions.assertNotEquals(scraps.size(), 0);

        Scrap scrap = scraps.get(0);
        Assertions.assertEquals(scrap.getCount(), 3);
    }

    @Test
    @Transactional
    @DisplayName("스크랩 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalScrap = this.scrapRepository.findById(1L);

        if(optionalScrap.isPresent()) {
            var result = optionalScrap.get();
            Assertions.assertEquals(result.getUser().getEmail(), "ssar@naver.com");

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
    @DisplayName("스크랩 삽입 및 삭제 테스트")
    void insertAndDelete() {
        Scrap scrap = setUpByScrap(15);
        Optional<Scrap> findUser = this.scrapRepository.findById(scrap.getId());

        if(findUser.isPresent()) {
            var result = findUser.get();
            Assertions.assertEquals(result.getUser().getName(), "love");
            entityManager.remove(scrap);
            Optional<Scrap> deleteUser = this.scrapRepository.findById(scrap.getId());
            if (deleteUser.isPresent()) {
                Assertions.assertNull(deleteUser.get());
            }
        } else {
            Assertions.assertNotNull(findUser.get());
        }
    }

    private Scrap setUpByScrap(int count) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Place place = new Place().builder().title("제목").address(address).tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                .startTime(LocalTime.from(LocalDateTime.now())).endTime(LocalTime.from(LocalDateTime.now())).build();
        this.entityManager.persist(place);

        Scrap scrap = new Scrap();
        scrap.setUser(user);
        scrap.setPlace(place);
        scrap.setCount(count);
        return this.entityManager.persist(scrap);
    }
}
