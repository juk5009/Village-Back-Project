package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.host.HostSaveDto;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.model.host.HostRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HostService {

    private final HostRepository hostRepository;

    public Host 호스트신청(HostSaveDto hostSaveDto) {
        return hostRepository.save(hostSaveDto.toEntity());
    }


}
