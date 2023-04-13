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
import shop.mtcoding.village.model.chat.Chat;
import shop.mtcoding.village.model.chat.ChatRepository;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE chat_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();

        User user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");
        Review review = new Review();
        review = setUpByReview(user, 5, "내용4", "image4", 4);

        Address address = new Address();
        address = setUpByAddress("부산 부산진구 중앙대로 688 한준빌딩 3층", "부산진구1", "47396", "121", "151");

        Place place = setUpByPlace(user, "제목3", address, "전번3", review, "공간정보3", "공간소개3", "시설정보3",
                "해쉬태그3", "image5", 5, 30, LocalDateTime.now(), LocalDateTime.now());

        ChatRoom chatRoom = setUpByChatRoom(user, place);

        setUpByChat(user, "하이", chatRoom);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Chat> chatList = chatRepository.findAll();
        Assertions.assertNotEquals(chatList.size(), 0);

        Chat chat = chatList.get(0);
        Assertions.assertEquals(chat.getUser().getName(), "love");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalChat = this.chatRepository.findById(4L);

        if(optionalChat.isPresent()) {
            var result = optionalChat.get();
            Assertions.assertEquals(result.getUser().getName(), "love");

            var profile = new User();
            profile.setProfile("profile-수정");
            result.setUser(profile);
            Chat merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getUser().getProfile(), "profile-수정");
        } else {
            Assertions.assertNotNull(optionalChat.get());
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

        Chat chat = setUpByChat(user, "ssar",chatRoom);
        Optional<Chat> findAddress = this.chatRepository.findById(chat.getId());

        if(findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getSend(), "ssar");
            entityManager.remove(chat);
            Optional<Chat> deleteChat = this.chatRepository.findById(chat.getId());
            if (deleteChat.isPresent()) {
                Assertions.assertNull(deleteChat.get());
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
    private Chat setUpByChat(User user, String send, ChatRoom chatRoom) {
        Chat chat = new Chat();
        chat.setUser(user);
        chat.setSend(send);
        chat.setChatRoom(chatRoom);
        return this.entityManager.persist(chat);
    }
}
