//package shop.mtcoding.village.interfaceTest;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import shop.mtcoding.village.model.user.User;
//
//import java.time.LocalDateTime;
//
//public class MockUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 인증 정보를 반환하는 코드 작성
//        return User.builder()
//                .username(username)
//                .password("1234")
//                .role()
//                .build();
//    }
//}
