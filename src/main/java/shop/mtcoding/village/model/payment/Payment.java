package shop.mtcoding.village.model.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PaymentStatus;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment_tb")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("결제 아이디")
    private Long id;

    @Comment("유저 정보")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Comment("공간 정보")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    @Comment("예약 정보")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    @JsonIgnore
    private Reservation reservation;

    @Comment("결제 상태")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Comment("주문 id")
    private String orderId;

    @Comment("주문 이름")
    private String orderName;

    @Comment("총결제 금액")
    private Integer totalPrice;


    @Builder
    public Payment(Long id, User user, Place place, Reservation reservation, PaymentStatus status, Integer totalPrice) {
        this.id = id;
        this.user = user;
        this.place = place;
        this.reservation = reservation;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Payment(String orderId, String orderName, Integer totalPrice) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.totalPrice = totalPrice;
    }
}
