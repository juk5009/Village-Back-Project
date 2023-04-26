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
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.chatRoom.ChatRoomRepository;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.facilityInfo.FacilityInfo;
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
@DisplayName("채팅방 JPA 테스트")
public class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

//    @BeforeEach
//    public void init() {
//        em.createNativeQuery("ALTER TABLE chat_room_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//
//        setUpByChatRoom();
//    }

    @Test
    @Transactional
    @DisplayName("채팅방 조회 테스트")
    void selectAll() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        Assertions.assertNotEquals(chatRooms.size(), 0);

        ChatRoom chatRoom = chatRooms.get(0);
        Assertions.assertEquals(chatRoom.getUser().getName(), "ssar");
    }

    @Test
    @Transactional
    @DisplayName("채팅방 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalChatRoom = this.chatRoomRepository.findById(1L);

        if (optionalChatRoom.isPresent()) {
            var result = optionalChatRoom.get();
            Assertions.assertEquals(result.getUser().getName(), "ssar");

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
    @DisplayName("채팅방 삽입 및 삭제 테스트")
    void insertAndDelete() {
        ChatRoom chatRoom = setUpByChatRoom();
        Optional<ChatRoom> findAddress = this.chatRoomRepository.findById(chatRoom.getId());

        if (findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getPlace().getTel(), "123123");
            entityManager.remove(chatRoom);
            Optional<ChatRoom> deleteAddress = this.chatRoomRepository.findById(chatRoom.getId());
            if (deleteAddress.isPresent()) {
                Assertions.assertNull(deleteAddress.get());
            }
        } else {
            Assertions.assertNotNull(findAddress.get());
        }
    }


    private ChatRoom setUpByChatRoom() {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Category category = new Category().builder().categoryName("이름").build();
        this.entityManager.persist(category);

        Place place = new Place().builder().title("제목").address(address).tel("123123").placeIntroductionInfo("공간정보").notice("공간소개")
                .startTime(LocalTime.from(LocalDateTime.now())).endTime(LocalTime.from(LocalDateTime.now())).build();
        this.entityManager.persist(place);

        ChatRoom chatRoom = new ChatRoom();
        this.entityManager.persist(category);
        chatRoom.setUser(user);
        chatRoom.setPlace(place);
        return this.entityManager.persist(chatRoom);
    }
}
