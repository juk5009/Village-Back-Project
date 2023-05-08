package shop.mtcoding.village.model.place;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.dto.place.response.PlaceUpdateResponse;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PlaceStatus;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "place_address_tb")
public class PlaceAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공간 아이디")
    private Long id;

    private String address;

    private String sigungu;

    private String zonecode;

    private String detailAddress;

    private String x; //lat lng

    private String y;

    @Builder
    public PlaceAddress(String address, String sigungu, String zonecode, String detailAddress, String x, String y) {
        this.address = address;
        this.sigungu = sigungu;
        this.zonecode = zonecode;
        this.detailAddress = detailAddress;
        this.x = x;
        this.y = y;
    }
}