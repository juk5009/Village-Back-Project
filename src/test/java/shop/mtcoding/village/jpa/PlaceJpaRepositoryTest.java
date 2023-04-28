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
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("공간 JPA 테스트")
public class PlaceJpaRepositoryTest {

    @Autowired
    private PlaceJpaRepository placeJpaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE place_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//        setUpByPlace("공간 제목","010-1245-7878", "공간 정보", "공간 소개",
//                 5, 30, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    @Transactional
    @DisplayName("공간 조회 테스트")
    void selectAll() {
        List<Place> places = placeJpaRepository.findAll();
        Assertions.assertNotEquals(places.size(), 0);

        Place place = places.get(0);
        Assertions.assertEquals(place.getTitle(), "스튜디오 르온드");
    }

    @Test
    @Transactional
    @DisplayName("공간 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalPlace = this.placeJpaRepository.findById(1L);

        if(optionalPlace.isPresent()) {
            var result = optionalPlace.get();
            Assertions.assertEquals(result.getTitle(), "스튜디오 르온드");

            var tel = "787878787778";
            result.setTel(tel);
            Place merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getTel(), "787878787778");
        } else {
            Assertions.assertNotNull(optionalPlace.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("공간 삽입 및 삭제 테스트")
    void insertAndDelete() {

        Place place = setUpByPlace("제목3","전번3", "공간정보3", "공간소개3",
                 5, 30, LocalDateTime.now(), LocalDateTime.now());
        Optional<Place> findPlace = this.placeJpaRepository.findById(place.getId());

        if(findPlace.isPresent()) {
            var result = findPlace.get();
            Assertions.assertEquals(result.getTitle(), "제목3");
            entityManager.remove(place);
            Optional<Place> deleteReview = this.placeJpaRepository.findById(place.getId());
            if (deleteReview.isPresent()) {
                Assertions.assertNull(deleteReview.get());
            }
        } else {
            Assertions.assertNotNull(findPlace.get());
        }
    }

    private Place setUpByPlace(
            String title, String tel, String placeIntroductionInfo, String notice, Integer maxPeople, Integer pricePerHour, LocalDateTime startTime, LocalDateTime endTime) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        var place = new Place();
        place.setUser(user);
        place.setTitle(title);
        place.setAddress(address);
        place.setTel(tel);
        place.setPlaceIntroductionInfo(placeIntroductionInfo);
        place.setNotice(notice);
        place.setMaxPeople(maxPeople);
        place.setPricePerHour(pricePerHour);
        place.setStartTime(LocalTime.from(startTime));
        place.setEndTime(LocalTime.from(endTime));
        return this.entityManager.persist(place);
    }
}
