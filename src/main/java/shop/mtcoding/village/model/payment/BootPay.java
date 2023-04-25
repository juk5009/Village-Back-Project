package shop.mtcoding.village.model.payment;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "boot_pay_tb")
public class BootPay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "receipt_id")
    private String receiptId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "tax_free")
    private Integer taxFree;

    @Column(name = "cancelled_price")
    private Integer cancelledPrice;

    @Column(name = "cancelled_tax_free")
    private Integer cancelledTaxFree;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "gateway_url")
    private String gatewayUrl;

    @Column(name = "sandbox")
    private Boolean sandbox;

    @Column(name = "pg")
    private String pg;

    @Column(name = "method")
    private String method;

    @Column(name = "method_symbol")
    private String methodSymbol;

    @Column(name = "method_origin")
    private String methodOrigin;

    @Column(name = "method_origin_symbol")
    private String methodOriginSymbol;

    @Column(name = "purchased_at")
    private OffsetDateTime purchasedAt;

    @Column(name = "cancelled_at")
    private OffsetDateTime cancelledAt;

    @Column(name = "requested_at")
    private OffsetDateTime requestedAt;

    @Column(name = "status_locale")
    private String statusLocale;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "status")
    private Integer status;

    ////////////// 결제완료
    private String currency;

    @Builder
    public BootPay(BootPay bootPay, String receiptId, String orderId, Integer price, Integer taxFree, Integer cancelledPrice, Integer cancelledTaxFree
            , String orderName, String companyName, String gatewayUrl, Boolean sandbox, String pg, String method, String methodSymbol, String methodOrigin
            , String methodOriginSymbol, OffsetDateTime purchasedAt, OffsetDateTime cancelledAt, OffsetDateTime requestedAt, String statusLocale, String receiptUrl, Integer status) {
        this.receiptId = receiptId;
        this.orderId = orderId;
        this.price = price;
        this.taxFree = taxFree;
        this.cancelledPrice = cancelledPrice;
        this.cancelledTaxFree = cancelledTaxFree;
        this.orderName = orderName;
        this.companyName = companyName;
        this.gatewayUrl = gatewayUrl;
        this.sandbox =sandbox;
        this.pg = pg;
        this.method = method;
        this.methodSymbol = methodSymbol;
        this.methodOrigin = methodOrigin;
        this.methodOriginSymbol = methodOriginSymbol;
        this.purchasedAt = purchasedAt;
        this.cancelledAt = cancelledAt;
        this.requestedAt = requestedAt;
        this.statusLocale = statusLocale;
        this.receiptUrl =receiptUrl;
        this.status = status;
    }

}