package shop.mtcoding.village.model.cardData;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "card_data_tb")
public class CardData {

    @Id
    private Long tid;

    private String cardApproveNo;

    private String cardNo;

    private String cardQuota;

    private String cardCompanyCode;

    private String cardCompany;

    @Builder
    public CardData(Long tid, String cardApproveNo, String cardNo, String cardQuota, String cardCompanyCode, String cardCompany) {
        this.tid = tid;
        this.cardApproveNo = cardApproveNo;
        this.cardNo = cardNo;
        this.cardQuota = cardQuota;
        this.cardCompanyCode = cardCompanyCode;
        this.cardCompany = cardCompany;
    }
}
