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
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
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
        setUpByPayment(PaymentStatus.WAIT, 40000);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        Assertions.assertNotEquals(paymentList.size(), 0);

        Payment payment = paymentList.get(0);
        Assertions.assertEquals(payment.getTotalPrice(), 40000);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalPayment = this.paymentRepository.findById(4L);

        if(optionalPayment.isPresent()) {
            var result = optionalPayment.get();
            Assertions.assertEquals(result.getTotalPrice(), 40000);

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
    void insertAndDelete() {
        Payment payment = setUpByPayment (PaymentStatus.WAIT, 50000);
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



    private User setUpByUser(String name, String password, String email, String tel, String role, String profile) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setTel(tel);
        user.setRole(role);
        user.setProfile(profile);
        return this.entityManager.persist(user);
    }
    private Review setUpByReview(User user, Integer starRating, String content, String image, Integer likeCount) {
        Review review = new Review();
        review.setUser(user);
        review.setStarRating(starRating);
        review.setContent(content);
        review.setImage(image);
        review.setLikeCount(likeCount);
        return this.entityManager.persist(review);
    }
    private Address setUpByAddress(String roadFullAddr, String sggNm, String zipNo, String lat, String lng) {
        var address = new Address();
        address.setRoadFullAddr(roadFullAddr);
        address.setSggNm(sggNm);
        address.setZipNo(zipNo);
        address.setLat(lat);
        address.setLng(lng);
        return this.entityManager.persist(address);
    }
    private Place setUpByPlace(User user, String title, Address address, String tel, Review review, String placeIntroductionInfo, String guide, String facilityInfo,
                        String hashtag, String image, Integer maxPeople, Integer pricePerHour, LocalDateTime startTime, LocalDateTime endTime) {
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
    private Reservation setUpByReservation(User user, Place place, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {
        var reservation = new Reservation();
        reservation.setUser(user);
        reservation.setPlace(place);
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setPeopleNum(peopleNum);
        reservation.setStatus(status);
        return  this.entityManager.persist(reservation);
    }

    private Payment setUpByPayment(PaymentStatus status, Integer totalPrice){
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Category category = new Category().builder().name("이름").build();
        this.entityManager.persist(category);

        Place place = new Place().builder().user(user).title("제목").address(address).tel("123123").review(review)
                .placeIntroductionInfo("공간정보").guide("공간소개").facilityInfo("시설정보").hashtag("해시태그").image("사진").maxPeople(4).pricePerHour(30)
                .startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).category(category).build();
        this.entityManager.persist(place);

        Reservation reservation = new Reservation().builder().user(user).place(place).date(LocalDateTime.now()).startTime(LocalDateTime.now()).endTime(LocalDateTime.now())
                .peopleNum(3).status(ReservationStatus.WAIT).build();
        this.entityManager.persist(reservation);

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPlace(place);
        payment.setReservation(reservation);
        payment.setStatus(status);
        payment.setTotalPrice(totalPrice);
        return this.entityManager.persist(payment);
    }
}
