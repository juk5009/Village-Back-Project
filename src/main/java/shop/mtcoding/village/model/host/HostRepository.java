package shop.mtcoding.village.model.host;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.util.status.HostStatus;

import java.util.List;
import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long>{

    Host findByUserId(Long user_id);
    List<Host> findByStatus(HostStatus status);

    Host findByPlaceId(Long place_id);

}
