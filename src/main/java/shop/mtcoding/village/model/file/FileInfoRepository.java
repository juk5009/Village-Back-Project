package shop.mtcoding.village.model.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileInfo,Long> {
    FileInfo findByType(FileType type);

}
