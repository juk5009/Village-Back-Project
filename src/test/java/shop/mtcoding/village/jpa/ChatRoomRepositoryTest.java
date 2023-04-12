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
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.chatRoom.ChatRoomRepository;

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
        setUp(4L, 4L);
    }

    @Test
    @Transactional
    void selectAll() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        Assertions.assertNotEquals(chatRooms.size(), 0);

        ChatRoom chatRoom = chatRooms.get(0);
        Assertions.assertEquals(chatRoom.getUserId(), 4L);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalChatRoom = this.chatRoomRepository.findById(4L);

        if(optionalChatRoom.isPresent()) {
            var result = optionalChatRoom.get();
            Assertions.assertEquals(result.getUserId(),4L);

            var placeId = 4L;
            result.setPlaceId(placeId);
            ChatRoom merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getPlaceId(),4L);
        } else {
            Assertions.assertNotNull(optionalChatRoom.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        ChatRoom chatRoom = setUp(5L, 5L);
        Optional<ChatRoom> findAddress = this.chatRoomRepository.findById(chatRoom.getId());

        if(findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getUserId(),5L);
            entityManager.remove(chatRoom);
            Optional<ChatRoom> deleteAddress = this.chatRoomRepository.findById(chatRoom.getId());
            if (deleteAddress.isPresent()) {
                Assertions.assertNull(deleteAddress.get());
            }
        } else {
            Assertions.assertNotNull(findAddress.get());
        }
    }


    private ChatRoom setUp(Long userId, Long placeId) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUserId(userId);
        chatRoom.setPlaceId(placeId);
        return this.entityManager.persist(chatRoom);
    }
}
