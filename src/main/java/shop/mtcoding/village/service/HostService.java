package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HostService {

    private final HostRepository hostRepository;

    @Transactional
    public Host 호스트신청(HostSaveRequest hostSaveDto) {
        try {
            return hostRepository.save(hostSaveDto.toEntity());
        } catch (Exception500 e) {
            throw new Exception500("호스트신청 오류" + e.getMessage());
        }

    }


}
