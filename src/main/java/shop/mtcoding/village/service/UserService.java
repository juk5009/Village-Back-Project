package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.core.jwt.MyJwtProvider;
import shop.mtcoding.village.dto.user.UserRequest;
import shop.mtcoding.village.dto.user.UserResponse;
import shop.mtcoding.village.model.fcm.FcmRepository;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.util.status.HostStatus;
import shop.mtcoding.village.util.status.UserStatus;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final FcmRepository fcmRepository;
    private final HostRepository hostRepository;

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
    public ArrayList<String> 로그인(UserRequest.LoginDTO loginDTO) {
        try {
            ArrayList<String> loginViewList = new ArrayList<>();

            Optional<User> userOP = userRepository.findByEmail(loginDTO.getEmail());
            if (userOP.isPresent()) {
                User userPS = userOP.get();

                if (passwordEncoder.matches(loginDTO.getPassword(), userPS.getPassword())) {
                    String jwt = MyJwtProvider.create(userPS);
                    loginViewList.add(jwt);
                    loginViewList.add(String.valueOf(userPS.getId()));
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

    @Transactional
    public User 유저비활성화(User user) {
        try {
            user.setStatus(UserStatus.INACTIVE);
            return userRepository.save(user);
        } catch (Exception500 e) {
            throw new Exception500("유저비활성화 오류" + e.getMessage());
        }
    }

    @Transactional
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User 호스트변경(User user) {
        try {
            Host byUserId = hostRepository.findByUserId(user.getId());

            byUserId.setStatus(HostStatus.SIGN);

            hostRepository.save(byUserId);

            user.setRole("HOST");
            userRepository.save(user);
        } catch (Exception e) {
            throw new Exception500("Host 변경에 실패 하였습니다 "+e.getMessage());
        }
        return user;
    }

    @Transactional
    public User 유저활성화(User user) {

        try {
            user.setStatus(UserStatus.ACTIVE);
            return userRepository.save(user);
        } catch (Exception500 e) {
            throw new Exception500("유저활성화 오류" + e.getMessage());
        }
    }
}


