package shop.mtcoding.village.model.fcm;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "fcm_tb")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Fcm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("fcm 아이디")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    private String targetToken;


    @Builder
    public Fcm(User user, String targetToken) {
        this.user = user;
        this.targetToken = targetToken;
    }

    public Fcm toEntity(Long id ,User user, String targetToken) {
        Fcm fcm = new Fcm();
        fcm.setId(id);
        fcm.setUser(user);
        fcm.setTargetToken(targetToken);
        return fcm;
    }

}
