package shop.mtcoding.village.model.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.util.status.NoticeStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice_tb")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("채팅 아이디")
    private Long id;
    @Comment("유저 아이디")
    private Long userId;
    @Comment("호스트 아이디")
    private Long placeId;
    @Comment("결제의 총 금액")
    // 결제테이블과 조인해서 총금액 받아오기
    private Integer paymentTotalPrice;
    @Comment("결제 내용")
    private String content;
    @Comment("알림 상태")
    private NoticeStatus status;



}
