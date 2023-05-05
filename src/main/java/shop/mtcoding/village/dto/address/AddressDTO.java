package shop.mtcoding.village.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String address;

    private String sigungu;

    private String zonecode;

    private String detailAddress;

    private String x;

    private String y;
}
