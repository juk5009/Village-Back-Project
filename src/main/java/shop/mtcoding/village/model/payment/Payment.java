package shop.mtcoding.village.model.payment;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PaymentStatus;

import javax.persistence.*;

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
    private User user;

    @Comment("공간 정보")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    @Comment("예약 정보")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Comment("결제 상태")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Comment("총결제 금액")
    private Integer totalPrice;

//    @Embedded
//    private BootPay boot;

    @Builder
    public Payment(Long id, User user, Place place, Reservation reservation, PaymentStatus status, Integer totalPrice) {
        this.id = id;
        this.user = user;
        this.place = place;
        this.reservation = reservation;
        this.status = status;
        this.totalPrice = totalPrice;
    }

//    public Payment(String receiptId, String orderId, Integer price, Integer taxFree, Integer cancelledPrice, Integer cancelledTaxFree, String orderName
//            , String companyName, String gatewayUrl, ReceiptDTO.Metadata metadata, Boolean sandbox, String pg, String method, String methodSymbol, String methodOrigin, String methodOriginSymbol, OffsetDateTime purchasedAt, OffsetDateTime cancelledAt, OffsetDateTime requestedAt, String statusLocale, String receiptUrl, Integer status, ReceiptDTO.CardData cardData) {
//        this.boot.setReceiptId(receiptId);
//        this.boot.setOrderId(orderId);
//        this.boot.setPrice(price);
//        this.boot.setTaxFree(taxFree);
//        this.boot.setCancelledPrice(cancelledPrice);
//        this.boot.setCancelledTaxFree(cancelledTaxFree);
//        this.boot.setOrderName(orderName);
//        this.boot.setCompanyName(companyName);
//        this.boot.setGatewayUrl(gatewayUrl);
//        this.boot.setMetadata((Map<String, Object>) metadata);
//        this.boot.setSandbox(sandbox);
//        this.boot.setPg();
//
//        ;
//    }
}
