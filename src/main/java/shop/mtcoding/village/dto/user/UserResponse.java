package shop.mtcoding.village.dto.user;

import lombok.*;
import shop.mtcoding.village.core.util.MyDateUtils;
import shop.mtcoding.village.model.user.User;

public class UserResponse {

    @Getter
    @Setter
    public static class JoinDTO {
        private Long id;
        private String name;
        private String email;
        private String createdAt;


        public JoinDTO(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.createdAt = MyDateUtils.toStringFormat(user.getCreatedAt());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class LoginDTO {
        private Long id;
        private String name;
        private String email;




    }
}