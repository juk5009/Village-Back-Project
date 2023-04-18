package shop.mtcoding.village.dto.host.request;

import lombok.Getter;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
public class HostSaveRequest {

    @NotBlank(message = "호스트의 이름을 다시 확인해주세요.")
    private String hostName;

    @NotBlank(message = "호스트의 주소를 다시 확인해주세요.")
    private String address;

    @NotBlank(message = "호스트의 사업자 번호를 다시 확인해주세요.")
    @Size(min = 10, max = 10, message = "사업자 번호는 10자리여야 합니다.")
    private String businessNum;



    public HostSaveRequest(String hostName, String address, String businessNum) {
        this.hostName = hostName;
        this.address = address;
        this.businessNum = businessNum;
    }

    public Host toEntity() {
        User user = new User();
        user.setName(hostName);
        return new Host(user, address, businessNum);
    }
}
