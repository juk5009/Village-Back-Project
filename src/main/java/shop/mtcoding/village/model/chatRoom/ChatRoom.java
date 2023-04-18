package shop.mtcoding.village.model.chatRoom;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chatroom_tb")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅방 아이디")
    private Long id;

    @Comment("유저 아이디")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("호스트 아이디")
    @OneToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Comment("채팅방 시간")
    private Timestamp createdAt;

    @Builder
    public ChatRoom(User user, Place place) {
        this.user = user;
        this.place = place;
    }
}
