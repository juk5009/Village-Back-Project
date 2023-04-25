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
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;
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
@DisplayName("결제 JPA 테스트")
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE payment_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//        setUpByPayment(PaymentStatus.WAIT, 40000);
    }

    @Test
    @Transactional
    @DisplayName("결제 조회 테스트")
    void selectAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        Assertions.assertNotEquals(paymentList.size(), 0);

        Payment payment = paymentList.get(0);
        Assertions.assertEquals(payment.getTotalPrice(), 30000);
    }

    @Test
    @Transactional
    @DisplayName("결제 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalPayment = this.paymentRepository.findById(1L);

        if(optionalPayment.isPresent()) {
            var result = optionalPayment.get();
            Assertions.assertEquals(result.getTotalPrice(), 30000);

            var totalPrice = 45000;
            result.setTotalPrice(totalPrice);
            Payment merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getTotalPrice(), 45000);
        } else {
            Assertions.assertNotNull(optionalPayment.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("결제 삽입 및 삭제 테스트")
    void insertAndDelete() {
        Payment payment = setUpByPayment (PaymentStatus.COMPLETE, 50000);
        Optional<Payment> findPayment = this.paymentRepository.findById(payment.getId());

        if(findPayment.isPresent()) {
            var result = findPayment.get();
            Assertions.assertEquals(result.getTotalPrice(), 50000);
            entityManager.remove(payment);
            Optional<Payment> deleteReview = this.paymentRepository.findById(payment.getId());
            if (deleteReview.isPresent()) {
                Assertions.assertNull(deleteReview.get());
            }
        } else {
            Assertions.assertNotNull(findPayment.get());
        }
    }



    private Payment setUpByPayment(PaymentStatus status, Integer totalPrice){
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
        ).totalPrice(50000).build(); payment.setUser(user);
        payment.setPlace(place);
        payment.setReservation(reservation);
        payment.setStatus(status);
        payment.setTotalPrice(totalPrice);
        return this.entityManager.persist(payment);
    }
}
