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
        setUpByChat("하이");
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
        Chat chat = setUpByChat("ssar");
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

    private Chat setUpByChat(String send) {
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

        ChatRoom chatRoom = new ChatRoom(user, place);
        this.entityManager.persist(chatRoom);
        Chat chat = new Chat();
        chat.setUser(user);
        chat.setSend(send);
        chat.setChatRoom(chatRoom);
        return this.entityManager.persist(chat);
    }
}
