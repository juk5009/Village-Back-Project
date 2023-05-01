package shop.mtcoding.village.model.address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "address_tb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("주소 아이디")
//    @JsonIgnore
    private Long id;

    @Comment("도로명 주소")
    private String roadFullAddr;

    @Comment("시군구명")
//    @JsonIgnore
    private String sggNm;

    @Comment("우편번호")
//    @JsonIgnore
    private String zipNo;

    @Comment("경도")
//    @JsonIgnore
    private String lat;

    @Comment("위도")
//    @JsonIgnore
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
