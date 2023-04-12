package shop.mtcoding.village.model.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.util.type.RoleType;

@Entity
@Table(name = "user_tb")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Comment("유저 이름")
    private String name;
    @Comment("유저 비밀번호")
    private String password;
    @Comment("유저 이메일")
    private String email;
    @Comment("유저 전화번호")
    private String tel;
    @Comment("권한")
    private String role; //USER, MANAGER, ADMIN
    @Comment("프로필")
    private String profile;
    @Comment("시간")
    private LocalDateTime createdAt; // db에는 timestamp로 변경되어 들어감

    @Builder
    public User(Long id, String name, String password, String email, String tel, String role, String profile, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.profile = profile;
        this.createdAt = createdAt;
    }

    @PrePersist // insert시 동작 / 비영속 -> 영속
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }


}
