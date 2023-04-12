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
import shop.mtcoding.village.model.chat.Chat;
import shop.mtcoding.village.model.chat.ChatRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp(4L, "하이",4L);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Chat> chatList = chatRepository.findAll();
        Assertions.assertNotEquals(chatList.size(), 0);

        Chat chat = chatList.get(0);
        Assertions.assertEquals(chat.getUserId(), 4L);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalChat = this.chatRepository.findById(4L);

        if(optionalChat.isPresent()) {
            var result = optionalChat.get();
            Assertions.assertEquals(result.getUserId(),4L);

            var placeId = 4L;
            result.setUserId(placeId);
            Chat merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getUserId(),4L);
        } else {
            Assertions.assertNotNull(optionalChat.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Chat chat = setUp(5L, "ssar",5L);
        Optional<Chat> findAddress = this.chatRepository.findById(chat.getId());

        if(findAddress.isPresent()) {
            var result = findAddress.get();
            Assertions.assertEquals(result.getUserId(),5L);
            entityManager.remove(chat);
            Optional<Chat> deleteChat = this.chatRepository.findById(chat.getId());
            if (deleteChat.isPresent()) {
                Assertions.assertNull(deleteChat.get());
            }
        } else {
            Assertions.assertNotNull(findAddress.get());
        }
    }


    private Chat setUp(Long userId, String send, Long chatRoomId) {
        Chat chat = new Chat();
        chat.setUserId(userId);
        chat.setSend(send);
        chat.setChatRoomId(chatRoomId);
        return this.entityManager.persist(chat);
    }
}
