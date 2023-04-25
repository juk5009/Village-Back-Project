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
import shop.mtcoding.village.model.facilityInfo.FacilityInfoRepository;
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
@DisplayName("편의시설 JPA 테스트")
public class FacilityInfoRepositoryTest {

    @Autowired
    private FacilityInfoRepository facilityInfoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE facility_info_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//        setUp("화장실");
    }

    @Test
    @Transactional
    @DisplayName("편의시설 조회 테스트")
    void selectAll() {
        List<FacilityInfo> facilityInfos = facilityInfoRepository.findAll();
        Assertions.assertNotEquals(facilityInfos.size(), 0);

        FacilityInfo facilityInfo = facilityInfos.get(0);
        Assertions.assertEquals(facilityInfo.getFacilityName(), "카페");
    }

    @Test
    @Transactional
    @DisplayName("편의시설 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalFacilityInfo = this.facilityInfoRepository.findById(1L);

        if (optionalFacilityInfo.isPresent()) {
            var result = optionalFacilityInfo.get();
            Assertions.assertEquals(result.getFacilityName(), "카페");

            String facilityInfo = "매점";
            result.setFacilityName(facilityInfo);
            FacilityInfo merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getFacilityName(), "매점");
        } else {
            Assertions.assertNotNull(optionalFacilityInfo.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("편의시설 삽입 및 삭제 테스트")
    void insertAndDelete() {
        FacilityInfo facilityInfo = setUp("공부방");
        Optional<FacilityInfo> findfacilityInfo = this.facilityInfoRepository.findById(facilityInfo.getId());

        if (findfacilityInfo.isPresent()) {
            var result = findfacilityInfo.get();
            Assertions.assertEquals(result.getFacilityName(), "공부방");
            entityManager.remove(facilityInfo);
            Optional<FacilityInfo> deleteDate = this.facilityInfoRepository.findById(facilityInfo.getId());
            if (deleteDate.isPresent()) {
                Assertions.assertNull(deleteDate.get());
            }
        } else {
            Assertions.assertNotNull(findfacilityInfo.get());
        }
    }

    private FacilityInfo setUp(String facilityInfoName) {

        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Place place = new Place().builder().title("제목").address(address).tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                .startTime(LocalTime.from(LocalDateTime.now())).endTime(LocalTime.from(LocalDateTime.now())).build();
        this.entityManager.persist(place);

        FacilityInfo facilityName = new FacilityInfo().builder().place(place).facilityName(facilityInfoName).build();
        return this.entityManager.persist(facilityName);
    }
}
