package shop.mtcoding.village.model.host;

import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "host_tb")
@Getter
@Setter
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User에서 role이 HOST인 유저의 NAME
    @Comment("호스트 이름")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    // 현재는 주소만 받아 오고 추후에 공간정보 추가
    @Comment("호스트 공간의 정보")
//    @OneToOne
//    private Place place;
    private String address;

    @Comment("사업자 번호")
    private String businessNum;

    @Builder
    public Host(User user, String address, String businessNum) {
        this.user = user;
        this.address = address;
        this.businessNum = businessNum;
    }

    public HostSaveRequest toResponse() {
        return new HostSaveRequest(user.getName(), address, businessNum);
    }
}
