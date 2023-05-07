package shop.mtcoding.village.dto.bootpay;

import lombok.*;
import shop.mtcoding.village.model.cardData.CardData;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDataDTO {

        private String tid;

        private String card_approve_no;

        private String card_no;

        private String card_interest;

        private String card_quota;

        private String card_company_code;

        private String card_company;

        private String card_type;

        @Builder
        public CardData toEntity() {
            return new CardData(tid, card_approve_no, card_no, card_quota, card_company_code, card_company);
        }

}
