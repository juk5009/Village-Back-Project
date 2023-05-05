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
 import shop.mtcoding.village.model.place.PlaceAddress;
 import shop.mtcoding.village.model.review.Review;
 import shop.mtcoding.village.model.scrap.Scrap;
 import shop.mtcoding.village.model.scrap.ScrapRepository;
 import shop.mtcoding.village.model.user.User;

 import javax.persistence.EntityManager;
 import java.time.LocalDateTime;
 import java.time.LocalTime;
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
     }

     @Test
     @Transactional
     @DisplayName("스크랩 조회 테스트")
     void selectAll() {
         List<Scrap> scraps = scrapRepository.findAll();
         Assertions.assertNotEquals(scraps.size(), 0);

         Scrap scrap = scraps.get(0);
         Assertions.assertEquals(scrap.getUser().getId(), 1);
     }

     @Test
     @Transactional
     @DisplayName("스크랩 조회 및 수정 테스트")
     void selectAndUpdate() {
         var optionalScrap = this.scrapRepository.findById(1L);

         if(optionalScrap.isPresent()) {
             var result = optionalScrap.get();
             Assertions.assertEquals(result.getUser().getEmail(), "ssar@naver.com");

             var userId = 4L;
             result.getUser().setId(userId);
             Scrap merge = entityManager.merge(result);

             Assertions.assertEquals(merge.getUser().getId(), 4L);
         } else {
             Assertions.assertNotNull(optionalScrap.get());
         }
     }

     @Test
     @Transactional
     @DisplayName("스크랩 삽입 및 삭제 테스트")
     void insertAndDelete() {
         Scrap scrap = setUpByScrap();
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

     private Scrap setUpByScrap() {
         User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
         this.entityManager.persist(user);

         PlaceAddress placeAddress = new PlaceAddress().builder().address("도로명주소").sigungu("시군구").zonecode("우편번호").x("경도").y("위도").build();
         this.entityManager.persist(placeAddress);

         Place place = new Place().builder().title("제목").tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                 .startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).build();
         this.entityManager.persist(place);

         Scrap scrap = new Scrap();
         scrap.setUser(user);
         scrap.setPlace(place);
         return this.entityManager.persist(scrap);
     }
 }
