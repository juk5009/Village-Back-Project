package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.dto.user.UserResponse;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 1. 트랜잭션 관리
     * 2. 영속성 객체 변경감지
     * 3. RequestDTO 요청받기
     * 4. 비지니스 로직 처리하기
     * 5. ResponseDTO 응답하기
     */
    @Transactional
    public UserResponse.JoinDTO 회원가입(UserRequest.JoinDTO joinDTO) {
        try {
            // select
            String rawPassword = joinDTO.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
            joinDTO.setPassword(encPassword);
            User userPS = userRepository.save(joinDTO.toEntity());


            return new UserResponse.JoinDTO(userPS);
        } catch (Exception500 e) {
            throw new Exception500("회원가입 오류" + e.getMessage());
        }


    }

    @Transactional
    public ArrayList 로그인(UserRequest.LoginDTO loginDTO) {
        try {
            ArrayList loginViewList = new ArrayList();

            Optional<User> userOP = userRepository.findByEmail(loginDTO.getEmail());
            if (userOP.isPresent()) {
                User userPS = userOP.get();
                if (passwordEncoder.matches(loginDTO.getPassword(), userPS.getPassword())) {
                    String jwt = MyJwtProvider.create(userPS);
                    loginViewList.add(jwt);
                    loginViewList.add(userPS.getId());
                    loginViewList.add(userPS.getName());
                    loginViewList.add(userPS.getEmail());


                    return loginViewList;

                }
                else {
                    throw new RuntimeException("패스워드 틀렸습니다.");
                }
            } else {
                throw new RuntimeException("존재하지않는 이메일입니다.");
            }
        }catch (Exception500 e) {
            throw new Exception500("로그인 오류" + e.getMessage());
        }


    }
}


