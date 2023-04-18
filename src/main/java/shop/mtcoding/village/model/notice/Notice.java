package shop.mtcoding.village.model.notice;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.NoticeStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notice_tb")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅 아이디")
    private Long id;

    @Comment("유저 아이디")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("호스트 아이디")
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @Comment("결제의 총 금액")
    @OneToOne
    @JoinColumn(name = "payment_id")
    // 결제테이블과 조인해서 총금액 받아오기
    private Payment payment;

    @Comment("결제 내용")
    private String content;

    @Comment("알림 상태")
    private NoticeStatus status;

    @Builder
    public Notice(User user, Place place, Payment payment, String content, NoticeStatus status) {
        this.user = user;
        this.place = place;
        this.payment = payment;
        this.content = content;
        this.status = status;
    }
}
