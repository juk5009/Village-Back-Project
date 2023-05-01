package shop.mtcoding.village.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.dto.user.UserResponse;
import shop.mtcoding.village.model.fcm.Fcm;
import shop.mtcoding.village.model.fcm.FcmRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final FcmRepository fcmRepository;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/join")
    public ResponseEntity<ResponseDTO<?>> join(@RequestBody @Valid UserRequest.JoinDTO joinDTO, Errors Errors) {

        // select 됨
        UserResponse.JoinDTO data = userService.회원가입(joinDTO);

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(data);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO loginDTO, Errors Errors) {

        ArrayList loginViewList = userService.로그인(loginDTO);
        String jwt = (String) loginViewList.get(0);
        UserResponse.LoginDTO loginViewDTO = new UserResponse.LoginDTO((Long) loginViewList.get(1),
                (String) loginViewList.get(2), (String) loginViewList.get(3));

        // User가 로그인 시 FcmToken 같이 넣기
        Fcm fcmToken = fcmRepository.findByTargetToken(loginDTO.getTargetToken());

        Optional<User> byId1 = userRepository.findById(loginViewDTO.getId());
        User user = byId1.get();
        fcmRepository.save(fcmToken.toEntity(fcmToken.getId(), user, loginDTO.getTargetToken()));

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(loginViewDTO);
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(responseDTO);
    }

    @GetMapping("/users/{id}") // 인증 확인
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> userCheck(@PathVariable Long id,
            @AuthenticationPrincipal MyUserDetails myUserDetails) {

        Long principalId = myUserDetails.getUser().getId();
        String role = myUserDetails.getUser().getRole();

        if (principalId != id) {
            return ResponseEntity.badRequest().body("올바른 접근이 아닙니다.");
        }

        return ResponseEntity.ok().body("id : " + principalId + " role : " + role);
    }

}
