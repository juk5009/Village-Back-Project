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
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;



    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE reservation_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
        User user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");
        Review review = setUpByReview(user, 5, "내용4", "image4", 4);
        Address address = setUpByAddress("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "121", "151");
        Place place = setUpByPlace(user, "제목3", address, "전번3", review, "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image3", 5, 30, LocalDateTime.now(), LocalDateTime.now());

        setUp(user, place, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 3, ReservationStatus.WAIT);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        Assertions.assertNotEquals(reservations.size(), 0);

        Reservation reservation = reservations.get(0);
        Assertions.assertEquals(reservation.getPeopleNum(), 3);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalReservation = this.reservationRepository.findById(4L);

        if(optionalReservation.isPresent()) {
            var result = optionalReservation.get();
            Assertions.assertEquals(result.getPeopleNum(), 3);

            var peopleNum = 4;
            result.setPeopleNum(peopleNum);
            Reservation merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getPeopleNum(), 4);
        } else {
            Assertions.assertNotNull(optionalReservation.get());
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

        Reservation reservation = setUp(user, place, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 5, ReservationStatus.WAIT);


        Optional<Reservation> findReservation = this.reservationRepository.findById(reservation.getId());

        if(findReservation.isPresent()) {
            var result = findReservation.get();
            Assertions.assertEquals(result.getPeopleNum(), 5);
            entityManager.remove(reservation);
            Optional<Reservation> deleteReservation = this.reservationRepository.findById(reservation.getId());
            if (deleteReservation.isPresent()) {
                Assertions.assertNull(deleteReservation.get());
            }
        } else {
            Assertions.assertNotNull(findReservation.get());
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

    private Reservation setUp(User user, Place place, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {
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
}
