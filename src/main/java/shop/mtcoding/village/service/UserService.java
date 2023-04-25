package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.dto.user.UserResponse;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
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
        // select
        String rawPassword = joinDTO.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword); // 60Byte
        joinDTO.setPassword(encPassword);
        User userPS = userRepository.save(joinDTO.toEntity());


        return new UserResponse.JoinDTO(userPS);
    }

    @Transactional
    public ArrayList 로그인(UserRequest.LoginDTO loginDTO) {
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

            } else {
                throw new RuntimeException("패스워드 틀렷어");
            }
        } else {
            throw new RuntimeException("존재하지않는 이메일입니다.");
        }


    }
}

//@Transactional(readOnly = true)
//public ArrayList 로그인(UserRequest.LoginDTO loginDTO) {
//
//        ArrayList loginOutList = new ArrayList();
//
//        Optional<User> userOP = userRepository.findByEmail(loginDTO.getEmail());
//        // 로그인 유저 아이디가 있다면
//        if (userOP.isPresent()) {
//        // 있으면 비밀번호 match (details를 안쓸거면 내가 비교해야되고, 암호화 된걸 처리해야 함)
//        User userPS = userOP.get();
//        // 로그인 입력 값과 DB password를 비교
//        if (passwordEncoder.matches(loginDTO.getPassword(), userPS.getPassword())) {
//        String jwt = MyJwtProvider.create(userPS); // 토큰 생성1
//        loginOutList.add(jwt);
//        loginOutList.add(userPS.getId());
//        loginOutList.add(userPS.getRole());
//        return loginOutList;
//
//        }
//        throw new Exception400("패스워드 틀렸어");
//        } else {
//        throw new Exception400("유저네임 없어");
//        }
//        }
