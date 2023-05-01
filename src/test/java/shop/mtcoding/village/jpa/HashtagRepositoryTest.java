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
import shop.mtcoding.village.model.hashtag.HashtagRepository;
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
@DisplayName("해시태그 JPA 테스트")
public class HashtagRepositoryTest {

    @Autowired
    private HashtagRepository hashtagInfoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

//    @BeforeEach
//    public void init() {
//        em.createNativeQuery("ALTER TABLE hashtag_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//        setUp("독서실");
//    }

    @Test
    @Transactional
    @DisplayName("해시태그 조회 테스트")
    void selectAll() {
        List<Hashtag> hashtags = hashtagInfoRepository.findAll();
        Assertions.assertNotEquals(hashtags.size(), 0);

        Hashtag hashtag = hashtags.get(0);
        Assertions.assertEquals(hashtag.getHashtagName(), "파티룸");
    }

    @Test
    @Transactional
    @DisplayName("해시태그 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalHashtag = this.hashtagInfoRepository.findById(1L);

        if (optionalHashtag.isPresent()) {
            var result = optionalHashtag.get();
            Assertions.assertEquals(result.getHashtagName(), "파티룸");

            String hashtag = "음식점";
            result.setHashtagName(hashtag);
            Hashtag merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getHashtagName(), "음식점");
        } else {
            Assertions.assertNotNull(optionalHashtag.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("해시태그 삽입 및 삭제 테스트")
    void insertAndDelete() {
        Hashtag hashtag = setUp("공부방");
        Optional<Hashtag> findHashtag = this.hashtagInfoRepository.findById(hashtag.getId());

        if (findHashtag.isPresent()) {
            var result = findHashtag.get();
            Assertions.assertEquals(result.getHashtagName(), "공부방");
            entityManager.remove(hashtag);
            Optional<Hashtag> deleteDate = this.hashtagInfoRepository.findById(hashtag.getId());
            if (deleteDate.isPresent()) {
                Assertions.assertNull(deleteDate.get());
            }
        } else {
            Assertions.assertNotNull(findHashtag.get());
        }
    }

    private Hashtag setUp(String hashtagName1) {

        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Place place = new Place().builder().title("제목").address(address).tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                .startTime(LocalTime.from(LocalDateTime.now())).endTime(LocalTime.from(LocalDateTime.now())).build();
        this.entityManager.persist(place);

        Hashtag hashtagName = new Hashtag().builder().hashtagName(hashtagName1).build();
        return this.entityManager.persist(hashtagName);
    }
}
