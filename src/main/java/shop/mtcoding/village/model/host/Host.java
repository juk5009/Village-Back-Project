package shop.mtcoding.village.model.host;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.dto.host.HostSaveDto;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "host_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User에서 role이 HOST인 유저의 NAME
    @Comment("호스트 이름")
    @OneToOne
    private User user;

    // 현재는 주소만 받아 오고 추후에 공간정보 추가
    @Comment("호스트 공간의 정보")
//    @OneToOne
//    private Place place;
    private String address;


    @Comment("사업자 번호")
    private String businessNum;

    public HostSaveDto toResponse() {
        return new HostSaveDto(user.getName(), address, businessNum);
    }
}
