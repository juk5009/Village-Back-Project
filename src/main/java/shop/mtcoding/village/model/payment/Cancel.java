package shop.mtcoding.village.model.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cancel {

    private String receiptId;

    private String cancleUsername;

    private String cancleMessage;
}
