package shop.mtcoding.village.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.dto.user.UserResponse;
import shop.mtcoding.village.exception.Exception400;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO joinDTO, BindingResult result) {

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());

        }
        // select 됨
        UserResponse.JoinDTO data = userService.회원가입(joinDTO);
        // select 안됨
        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(data);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());

        }

        String jwt = userService.로그인(loginDTO);
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body("로그인 성공하였습니다.");
    }

    @GetMapping("/ct/users/{id}") //인증 확인
    public ResponseEntity<?> userCheck(@PathVariable Long id,
            @AuthenticationPrincipal MyUserDetails myUserDetails ) {




        Long principalId = myUserDetails.getUser().getId();
        String role = myUserDetails.getUser().getRole();

        if (principalId != id){
            return ResponseEntity.badRequest().body("올바른 접근이 아닙니다. ");
        }

        return ResponseEntity.ok().body("id : "+ principalId + " role : " + role);
    }


}
