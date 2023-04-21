package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;
import shop.mtcoding.village.util.status.HostStatus;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HostService {

    private final HostRepository hostRepository;

    private final UserRepository userRepository;

    @Transactional
    public Host 호스트신청(HostSaveRequest hostSaveDto) {
        try {
            User byName = userRepository.findByName(hostSaveDto.getHostName());

            hostSaveDto.setStatus(HostStatus.WAIT);
            return hostRepository.save(hostSaveDto.toEntity(byName));
        } catch (Exception500 e) {
            throw new Exception500("호스트신청 오류" + e.getMessage());
        }
    }
}
