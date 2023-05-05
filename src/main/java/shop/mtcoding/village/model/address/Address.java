package shop.mtcoding.village.model.address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;


// TODO address는 Enity로 쓰지 말것!
//@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
//@Table(name = "address_tb")
public class Address {

    private String address;

    private String sigungu;

    private String zonecode;

    private String detailAddress;

    private String x;

    private String y;


}
