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
    private String address;

    @Comment("시군구명")
//    @JsonIgnore
    private String sigungu;

    @Comment("우편번호")
//    @JsonIgnore
    private String zonecode;

    @Comment("상세주소")
    private String detailAddress;

    @Comment("경도")
//    @JsonIgnore
    private String x;

    @Comment("위도")
//    @JsonIgnore
    private String y;

    @Builder
    public Address(String address, String sigungu, String zonecode, String detailAddress, String x, String y) {
        this.address = address;
        this.sigungu = sigungu;
        this.zonecode = zonecode;
        this.detailAddress = detailAddress;
        this.x = x;
        this.y = y;
    }
}
