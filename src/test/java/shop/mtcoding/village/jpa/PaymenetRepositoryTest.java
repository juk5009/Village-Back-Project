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
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PaymentStatus;
import shop.mtcoding.village.util.status.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PaymenetRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        User user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");
        Review review = setUpByReview(user, 5, "내용4", "image4", 4);
        Address address = setUpByAddress("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "121", "151");
        Place place = setUpByPlace(user, "제목3", address, "전번3", review, "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image3", 5, 30, LocalDateTime.now(), LocalDateTime.now());
        Reservation reservation = setUpByReservation(user, place, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 3, ReservationStatus.WAIT);
        setUpByPayment(user, place, reservation, PaymentStatus.WAIT, 40000);
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
        User user = new User();
        user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");

        Review review = new Review();
        review = setUpByReview(user, 5, "내용4", "image4", 4);

        Address address = new Address();
        address = setUpByAddress("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "121", "151");

        Place place = setUpByPlace(user, "제목3", address, "전번3", review, "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image5", 5, 30, LocalDateTime.now(), LocalDateTime.now());

        Reservation reservation = setUpByReservation(user, place, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 5, ReservationStatus.WAIT);

        Payment payment = setUpByPayment(user, place, reservation, PaymentStatus.WAIT, 50000);
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

    private Payment setUpByPayment(
            User user, Place place, Reservation reservation, PaymentStatus status, Integer totalPrice){
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPlace(place);
        payment.setReservation(reservation);
        payment.setStatus(status);
        payment.setTotalPrice(totalPrice);
        return this.entityManager.persist(payment);
    }
}
