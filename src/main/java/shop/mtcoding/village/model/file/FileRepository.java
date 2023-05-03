package shop.mtcoding.village.model.file;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {

    File findByPlaceId(Long place_id);
}
