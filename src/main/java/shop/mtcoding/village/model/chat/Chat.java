package shop.mtcoding.village.model.chat;


import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chat_tb")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅 아이디")
    private Long id;

    @Comment("유저(호스트)아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Comment("채팅글")
    private String send;

    @Comment("채팅방 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ChatRoom chatRoom;

    @Comment("채팅한 시간")
    private Timestamp createdAt;

    @Builder
    public Chat(User user, String send, ChatRoom chatRoom) {
        this.user = user;
        this.send = send;
        this.chatRoom = chatRoom;
    }
}
