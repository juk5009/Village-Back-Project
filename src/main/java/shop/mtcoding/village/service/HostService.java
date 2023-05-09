package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.api.firebase.FirebaseCloudMessageService;
import shop.mtcoding.village.api.firebase.RequestDTO;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.model.fcm.Fcm;
import shop.mtcoding.village.model.fcm.FcmRepository;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.util.DateUtils;
import shop.mtcoding.village.util.status.HostStatus;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HostService {

    private final HostRepository hostRepository;

    private final UserRepository userRepository;

    private final FcmRepository fcmRepository;

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @Transactional
    public Host 호스트신청(HostSaveRequest hostSaveDto) {
        try {
            User user = userRepository.findByName(hostSaveDto.getHostName());
            if (user.getName() == hostSaveDto.getHostName()) {
                throw new CustomException("이미 신청을 하셨습니다.");
            }

            var fcmOptional = fcmRepository.findByUserId(user.getId());

            if (fcmOptional.isEmpty()) {
                throw new CustomException("조회 할 수 있는 토큰이 존재하지 않습니다.");
            }

            Fcm fcm = fcmOptional.get();

            Host host = hostRepository.findByUserId(user.getId());
            host.setStatus(HostStatus.WAIT);

            RequestDTO requestDTO = new RequestDTO("Village",
                    "[호스트신청알림]\n"+ user.getName()+ "님이 호스트 신청했습니다.\n",
                    fcm.getTargetToken());

            firebaseCloudMessageService.sendMessageTo(
                    requestDTO.getTargetToken(),
                    requestDTO.getTitle(),
                    requestDTO.getBody());

            return hostRepository.save(host);
        } catch (Exception500 e) {
            throw new Exception500("호스트신청 오류" + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
