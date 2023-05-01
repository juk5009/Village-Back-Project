package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.notice.Notice;
import shop.mtcoding.village.model.notice.NoticeRepository;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.NoticeStatus;
import shop.mtcoding.village.util.status.PaymentStatus;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("알림 JPA 테스트")
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

//    @BeforeEach
//    public void init() {
//        em.createNativeQuery("ALTER TABLE notice_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//
//        setUpByNotice("내용4", NoticeStatus.WAIT);
//    }

    @Test
    @Transactional
    @DisplayName("알림 조회 테스트")
    void selectAll() {
        List<Notice> notices = noticeRepository.findAll();
        Assertions.assertNotEquals(notices.size(), 0);

        Notice notice = notices.get(0);
        Assertions.assertEquals(notice.getContent(), "내용1");
    }

    @Test
    @Transactional
    @DisplayName("알림 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalNotice = this.noticeRepository.findById(1L);

        if(optionalNotice.isPresent()) {
            var result = optionalNotice.get();
            Assertions.assertEquals(result.getUser().getName(), "ssar");

            var paymentTotalPrice = new Payment();
            paymentTotalPrice.setTotalPrice(50000);
            result.setPayment(paymentTotalPrice);
            Notice merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getPayment().getTotalPrice(), 50000);
        } else {
            Assertions.assertNotNull(optionalNotice.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("알림 삽입 및 삭제 테스트")
    void insertAndDelete() {
        Notice notice = setUpByNotice("내용5", NoticeStatus.WAIT);

        Optional<Notice> findNotice = this.noticeRepository.findById(notice.getId());

        if(findNotice.isPresent()) {
            var result = findNotice.get();
            Assertions.assertEquals(result.getPayment().getTotalPrice(), 50000);
            entityManager.remove(notice);
            Optional<Notice> deleteAccount = this.noticeRepository.findById(notice.getId());
            if (deleteAccount.isPresent()) {
                Assertions.assertNull(deleteAccount.get());
            }
        } else {
            Assertions.assertNotNull(findNotice.get());
        }
    }

    private Notice setUpByNotice(String content, NoticeStatus status) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Place place = new Place().builder().title("제목").address(address).tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                .startTime(LocalTime.from(LocalDateTime.now())).endTime(LocalTime.from(LocalDateTime.now())).build();
        this.entityManager.persist(place);

        Reservation reservation = new Reservation().builder().user(user).place(place).date(LocalDateTime.now()).startTime(LocalDateTime.now()).endTime(LocalDateTime.now())
                .peopleNum(3).status(ReservationStatus.WAIT).build();
        this.entityManager.persist(reservation);


        Payment payment = new Payment().builder().user(user).place(place).reservation(reservation).status(
                PaymentStatus.WAIT
        ).totalPrice(50000).build();
        this.entityManager.persist(payment);

        Notice notice = new Notice();
        notice.setUser(user);
        notice.setPlace(place);
        notice.setPayment(payment);
        notice.setContent(content);
        notice.setStatus(status);
        return this.entityManager.persist(notice);
    }
}
