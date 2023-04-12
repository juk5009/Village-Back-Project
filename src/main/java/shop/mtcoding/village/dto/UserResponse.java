package shop.mtcoding.village.dto;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.core.util.MyDateUtils;
import shop.mtcoding.village.model.User;

public class UserResponse {
    
    @Getter
    @Setter
    public static class JoinDTO {
        private Long id;
        private String username;
        private String email;
        private String role;
        private String createdAt;


        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.role = user.getRole();
            this.createdAt = MyDateUtils.toStringFormat(user.getCreatedAt());
        }
    }
}
