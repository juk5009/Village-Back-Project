package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return hostRepository.save(hostSaveDto.toEntity());
    }


}
