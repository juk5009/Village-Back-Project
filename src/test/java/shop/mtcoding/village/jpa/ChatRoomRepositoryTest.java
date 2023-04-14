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

    @BeforeEach
    public void init() {
        setUpByChatRoom();
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

        if (optionalChatRoom.isPresent()) {
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

        Category category = new Category().builder().name("이름").build();
        this.entityManager.persist(category);


        Place place = new Place().builder().user(user).title("제목").address(address).tel("123123").review(review)
                .placeIntroductionInfo("공간정보").guide("공간소개").facilityInfo("시설정보").hashtag("해시태그").image("사진").maxPeople(4).pricePerHour(30)
                .startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).category(category).build();
        this.entityManager.persist(place);

        ChatRoom chatRoom = new ChatRoom();
        this.entityManager.persist(category);
        chatRoom.setUser(user);
        chatRoom.setPlace(place);
        return this.entityManager.persist(chatRoom);
    }
}
