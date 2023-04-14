package shop.mtcoding.village.model.address;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "address_tb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("주소 아이디")
    private Long id;
    @Comment("도로명 주소")
    private String roadFullAddr;
    @Comment("시군구명")
    private String sggNm;
    @Comment("우편번호")
    private String zipNo;
    @Comment("경도")
    private String lat;
    @Comment("위도")
    private String lng;

    @Builder
    public Address(String roadFullAddr, String sggNm, String zipNo, String lat, String lng) {
        this.roadFullAddr = roadFullAddr;
        this.sggNm = sggNm;
        this.zipNo = zipNo;
        this.lat = lat;
        this.lng = lng;
    }
}
