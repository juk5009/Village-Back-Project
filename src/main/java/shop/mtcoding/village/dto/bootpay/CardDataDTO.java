package shop.mtcoding.village.dto.bootpay;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.village.model.cardData.CardData;

@Getter
@Setter
@NoArgsConstructor
public class CardDataDTO {

        private Long tid;

        private String card_approve_no;

        private String card_no;

        private String card_quota;

        private String card_company_code;

        private String card_company;

        @Builder
        public CardData toEntity() {
            return new CardData(tid, card_approve_no, card_no, card_quota, card_company_code, card_company);
        }

}
