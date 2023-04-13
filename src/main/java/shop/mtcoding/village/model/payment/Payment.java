package shop.mtcoding.village.model.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PaymentStatus;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_tb")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("결제 아이디")
    private Long id;
    @Comment("유저 정보")
    @OneToOne
    private User user;
    @Comment("공간 정보")
    @OneToOne
    private Place place;
    @Comment("예약 정보")
    @OneToOne
    private Reservation reservation;
    @Comment("결제 상태")
    private PaymentStatus status;
    @Comment("총결제 금액")
    private Integer totalPrice;



}
