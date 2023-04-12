package shop.mtcoding.village.model.chatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.chat.Chat;

public interface ChatRoomRepository extends JpaRepository<Chat,Long> {
}
