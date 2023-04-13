package shop.mtcoding.village.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.model.user.User;

public class UserRequest {

    @Getter
    @Setter
    public static class JoinDTO {
        private String email;
        private String password;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    // username 필터를 리빌딩하는건 추천하지 않음 dto 에서 AOP사용하는게 더 편함 
    @Getter
    @Setter
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
