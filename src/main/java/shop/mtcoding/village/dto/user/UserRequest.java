package shop.mtcoding.village.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.user.User;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRequest {

    @Getter
    @Setter
    public static class JoinDTO {
        @NotEmpty(message = "nickname을 입력해주세요.")
        private String name;
        @Email(message = "유효한 email 형식이 아닙니다.")
        @NotEmpty(message = "email을 입력해주세요.")
        private String email;
        @NotEmpty(message = "password 입력해주세요.")
        private String password;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    // username 필터를 리빌딩하는건 추천하지 않음 dto 에서 AOP사용하는게 더 편함 
    @Getter
    @Setter
    @ToString
    public static class LoginDTO {
        @NotEmpty(message = "E-mail을 입력해주세요.")
        @Email(message = "유효한 email 형식이 아닙니다.")
        private String email;
        @NotEmpty(message = "Password를 입력해주세요.")
        private String password;

        private String targetToken;
    }
}
