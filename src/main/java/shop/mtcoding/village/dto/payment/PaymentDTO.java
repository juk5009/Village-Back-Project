package shop.mtcoding.village.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.util.status.PaymentStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    @JsonProperty("receipt_id")
    private String receiptId;

    private Integer price;

    private PaymentStatus status;

}