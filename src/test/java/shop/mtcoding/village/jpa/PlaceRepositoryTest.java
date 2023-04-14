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
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE place_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
        setUpByPlace("제목3","전번3", "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image3", 5, 30, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    @Transactional
    void selectAll() {
        List<Place> places = placeRepository.findAll();
        Assertions.assertNotEquals(places.size(), 0);

        Place place = places.get(0);
        Assertions.assertEquals(place.getTitle(), "제목3");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalPlace = this.placeRepository.findById(4L);

        if(optionalPlace.isPresent()) {
            var result = optionalPlace.get();
            Assertions.assertEquals(result.getTitle(), "제목3");

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
    void insertAndDelete() {

        Place place = setUpByPlace("제목3","전번3", "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image5", 5, 30, LocalDateTime.now(), LocalDateTime.now());
        Optional<Place> findPlace = this.placeRepository.findById(place.getId());

        if(findPlace.isPresent()) {
            var result = findPlace.get();
            Assertions.assertEquals(result.getImage(), "image5");
            entityManager.remove(place);
            Optional<Place> deleteReview = this.placeRepository.findById(place.getId());
            if (deleteReview.isPresent()) {
                Assertions.assertNull(deleteReview.get());
            }
        } else {
            Assertions.assertNotNull(findPlace.get());
        }
    }

    private Place setUpByPlace(String title, String tel, String placeIntroductionInfo, String guide, String facilityInfo,
                        String hashtag, String image, Integer maxPeople, Integer pricePerHour, LocalDateTime startTime, LocalDateTime endTime) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Category category = new Category().builder().name("이름").build();


        var place = new Place();
        place.setUser(user);
        place.setTitle(title);
        place.setAddress(address);
        place.setTel(tel);
        place.setReview(review);
        place.setPlaceIntroductionInfo(placeIntroductionInfo);
        place.setGuide(guide);
        place.setFacilityInfo(facilityInfo);
        place.setHashtag(hashtag);
        place.setImage(image);
        place.setMaxPeople(maxPeople);
        place.setPricePerHour(pricePerHour);
        place.setStartTime(startTime);
        place.setEndTime(endTime);
        return this.entityManager.persist(place);
    }
}
