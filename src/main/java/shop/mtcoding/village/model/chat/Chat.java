package shop.mtcoding.village.model.chat;


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
@Table(name = "chat_tb")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅 아이디")
    private Long id;
    @Comment("유저(호스트)아이디")
    private Long userId;
    @Comment("채팅글")
    private String send;
    @Comment("채팅방 아이디")
    private Long chatRoomId;
    @Comment("채팅한 시간")
    private Timestamp createdAt;
}
