package shop.mtcoding.village.model.address;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
