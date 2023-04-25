package shop.mtcoding.village.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.payment.BootPay;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReceiptDTO {
    @JsonProperty("receipt_id")
    private String receiptId;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("tax_free")
    private Integer taxFree;

    @JsonProperty("cancelled_price")
    private Integer cancelledPrice;

    @JsonProperty("cancelled_tax_free")
    private Integer cancelledTaxFree;

    @JsonProperty("order_name")
    private String orderName;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("gateway_url")
    private String gatewayUrl;

    private Boolean sandbox;

    private String pg;

    private String method;

    @JsonProperty("method_symbol")
    private String methodSymbol;

    @JsonProperty("method_origin")
    private String methodOrigin;

    @JsonProperty("method_origin_symbol")
    private String methodOriginSymbol;

    @JsonProperty("purchased_at")
    private OffsetDateTime purchasedAt;

    @JsonProperty("cancelled_at")
    private OffsetDateTime cancelledAt;

    @JsonProperty("requested_at")
    private OffsetDateTime requestedAt;

    @JsonProperty("status_locale")
    private String statusLocale;

    @JsonProperty("receipt_url")
    private String receiptUrl;

    private Integer status;

    private CardDataDTO cardDataDTO;

    private MetadataDTO metadataDTO;

    public BootPay toEntity() {

        return new BootPay(null, this.receiptId, this.orderId, this.price, this.taxFree, this.cancelledPrice, this.cancelledTaxFree,
                this.orderName, this.companyName, this.gatewayUrl, this.sandbox, this.pg,
                this.method, this.methodSymbol, this.methodOrigin, this.methodOriginSymbol, this.purchasedAt,
                this.cancelledAt, this.requestedAt, this.statusLocale, this.receiptUrl, this.status);
    }

}