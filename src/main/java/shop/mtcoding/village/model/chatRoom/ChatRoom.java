package shop.mtcoding.village.model.chatRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chatRoom_tb")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅방 아이디")
    private Long id;
    @Comment("유저 아이디")
    private Long userId;
    @Comment("호스트 아이디")
    private Long placeId;
    @Comment("채팅방 시간")
    private Timestamp createdAt;
}
