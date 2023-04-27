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
import shop.mtcoding.village.model.address.AddressRepository;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.date.DateRepository;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("요일 JPA 테스트")
public class DatesRepositoryTest {

    @Autowired
    private DateRepository dateRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
//        em.createNativeQuery("ALTER TABLE dates_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//        setUp("월요일");
    }

    @Test
    @Transactional
    @DisplayName("요일 조회 테스트")
    void selectAll() {
        List<Dates> dates = dateRepository.findAll();
        Assertions.assertNotEquals(dates.size(), 0);

        Dates date = dates.get(0);
        Assertions.assertEquals(date.getDayOfWeekName(), "월요일");
    }

    @Test
    @Transactional
    @DisplayName("요일 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalDates = this.dateRepository.findById(1L);

        if (optionalDates.isPresent()) {
            var result = optionalDates.get();
            Assertions.assertEquals(result.getDayOfWeekName(), "월요일");

            String day = "화요일";
            result.setDayOfWeekName(day);
            Dates merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getDayOfWeekName(), "화요일");
        } else {
            Assertions.assertNotNull(optionalDates.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("요일 삽입 및 삭제 테스트")
    void insertAndDelete() {
        Dates dates = setUp("토요일");
        Optional<Dates> findDate = this.dateRepository.findById(dates.getId());

        if (findDate.isPresent()) {
            var result = findDate.get();
            Assertions.assertEquals(result.getDayOfWeekName(), "토요일");
            entityManager.remove(dates);
            Optional<Dates> deleteDate = this.dateRepository.findById(dates.getId());
            if (deleteDate.isPresent()) {
                Assertions.assertNull(deleteDate.get());
            }
        } else {
            Assertions.assertNotNull(findDate.get());
        }
    }

    private Dates setUp(String dayOfWeekName) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Category category = new Category().builder().categoryName("이름").build();
        this.entityManager.persist(category);

        Place place = new Place().builder().title("제목").address(address).tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                .startTime(LocalTime.from(LocalDateTime.now())).endTime(LocalTime.from(LocalDateTime.now())).build();
        this.entityManager.persist(place);

        Dates dates = new Dates().builder().place(place).dayOfWeekName(dayOfWeekName).build();
        return this.entityManager.persist(dates);

    }
}
