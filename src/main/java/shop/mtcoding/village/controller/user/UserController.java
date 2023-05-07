package shop.mtcoding.village.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.dto.user.UserResponse;
import shop.mtcoding.village.model.fcm.Fcm;
import shop.mtcoding.village.model.fcm.FcmRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.notFoundConst.UserConst;
import shop.mtcoding.village.service.UserService;
import shop.mtcoding.village.util.status.UserStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {


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


    @GetMapping("/jwtToken")
    public ResponseEntity<ResponseDTO<?>> jwtToken() {
        String jwtToken = MyJwtProvider.createToken(); // JWT 토큰 생성
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwtToken).body(responseDTO);
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

        ArrayList<String> loginViewList = userService.로그인(loginDTO);

        String jwt = (String) loginViewList.get(0);
        UserResponse.LoginDTO loginViewDTO = new UserResponse.LoginDTO(Long.parseLong(loginViewList.get(1)) ,
                (String) loginViewList.get(2), (String) loginViewList.get(3));

        // User가 로그인 시 FcmToken 같이 넣기
        Optional<Fcm> fcmTokenOptional = fcmRepository.findByTargetToken(loginDTO.getTargetToken());
        if (fcmTokenOptional.isEmpty()){
            throw new CustomException("해당 토큰이 존재하지 않습니다");
        }

        Fcm fcmToken = fcmTokenOptional.get();

        Optional<User> userOptional = userRepository.findById(loginViewDTO.getId());
        if (userOptional.isEmpty()){
            throw new CustomException("유저를 조회할 수 없습니다.");
        }

        User user = userOptional.get();

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

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO<UserStatus>> delete(
            @PathVariable Long id
    ) {
        var optionalUser = userService.getUser(id);
        if (optionalUser.isEmpty()) {
            throw new MyConstException(UserConst.notfound);
        }
        User inactiveUser = userService.유저비활성화(optionalUser.get());
        return new ResponseEntity<>(new ResponseDTO<>(1, 200 , "유저비활성화 완료", inactiveUser.getStatus()), HttpStatus.OK);
    }

    @PostMapping("/users/{id}")
    public ResponseEntity<ResponseDTO<UserStatus>> active(
            @PathVariable Long id
    ) {
        var optionalUser = userService.getUser(id);
        if (optionalUser.isEmpty()) {
            throw new MyConstException(UserConst.notfound);
        }
        User ActiveUser = userService.유저활성화(optionalUser.get());
        return new ResponseEntity<>(new ResponseDTO<>(1, 200 , "유저활성화 완료", ActiveUser.getStatus()), HttpStatus.OK);
    }

    @PostMapping("/users/host/{id}")
    public ResponseEntity<ResponseDTO<String>> host(
            @PathVariable Long id
    ) {
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isEmpty()) {
            throw new CustomException("유저에 대한 정보가 없습니다.");
        }

        User user = userOptional.get();

        User user1 = userService.호스트변경(user);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "Host 변경 성공", user1.getRole()),HttpStatus.OK);
    }

}
