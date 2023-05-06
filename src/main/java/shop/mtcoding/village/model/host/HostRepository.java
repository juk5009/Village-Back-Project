package shop.mtcoding.village.model.host;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long>{

    Host findByUser_Id(Long user_id);

}
