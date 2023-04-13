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
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.chatRoom.ChatRoomRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        User user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");

        Review review = new Review();
        review = setUpByReview(user, 5, "내용4", "image4", 4);

        Address address = new Address();
        address = setUpByAddress("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "121", "151");

        Place place = setUpByPlace(user, "제목3", address, "전번3", review, "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image5", 5, 30, LocalDateTime.now(), LocalDateTime.now());

        setUpByChatRoom(user, place);
    }

    @Test
    @Transactional
    void selectAll() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        Assertions.assertNotEquals(chatRooms.size(), 0);

        ChatRoom chatRoom = chatRooms.get(0);
        Assertions.assertEquals(chatRoom.getUser().getName(), "love");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalChatRoom = this.chatRoomRepository.findById(4L);

        if(optionalChatRoom.isPresent()) {
            var result = optionalChatRoom.get();
            Assertions.assertEquals(result.getUser().getName(), "love");

            Place title = new Place();
            title.setTitle("제목555");
            result.setPlace(title);
            ChatRoom merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getPlace().getTitle(), "제목555");
        } else {
            Assertions.assertNotNull(optionalChatRoom.get());
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
        ChatRoom chatRoom = setUpByChatRoom(user, place);
        Optional<ChatRoom> findAddress = this.chatRoomRepository.findById(chatRoom.getId());

        if(findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getPlace().getTel(), "전번3");
            entityManager.remove(chatRoom);
            Optional<ChatRoom> deleteAddress = this.chatRoomRepository.findById(chatRoom.getId());
            if (deleteAddress.isPresent()) {
                Assertions.assertNull(deleteAddress.get());
            }
        } else {
            Assertions.assertNotNull(findAddress.get());
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
    private ChatRoom setUpByChatRoom(User user, Place place) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUser(user);
        chatRoom.setPlace(place);
        return this.entityManager.persist(chatRoom);
    }
}
