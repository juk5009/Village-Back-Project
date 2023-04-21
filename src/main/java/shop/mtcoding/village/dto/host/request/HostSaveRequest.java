package shop.mtcoding.village.dto.host.request;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.dto.user.UserDTO;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.HostStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
public class HostSaveRequest {

    @NotBlank(message = "호스트의 이름을 다시 확인해주세요.")
    private String hostName;

    private String nickname;

//    @NotBlank(message = "호스트의 주소를 다시 확인해주세요.")
    private Address address;

    @NotBlank(message = "호스트의 사업자 번호를 다시 확인해주세요.")
    @Size(min = 10, max = 10, message = "사업자 번호는 10자리여야 합니다.")
    private String businessNum;

    private HostStatus status;




    public HostSaveRequest(String hostName, Address address, String businessNum, String nickname) {
        this.hostName = hostName;
        this.address = address;
        this.businessNum = businessNum;
        this.nickname = nickname;
    }

    public Host toEntity(User user) {
        user.setId(user.getId());
        user.setName(hostName);
        return new Host(user, address, nickname,  businessNum, status);
    }
}
