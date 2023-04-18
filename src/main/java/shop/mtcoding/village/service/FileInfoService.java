package shop.mtcoding.village.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.file.request.FileInfoSaveRequest;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.file.FileInfoRepository;

import java.util.Optional;

@Service
public class FileInfoService {

    private final FileInfoRepository fileInfoRepository;

    public FileInfoService(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }


    public Page<FileInfo> getPage(Pageable pageable) {
        return fileInfoRepository.findAll(pageable);
    }

    public Optional<FileInfo> getFileInfo(Long id) {
        return fileInfoRepository.findById(id);
    }

    @Transactional
    public FileInfo save(FileInfoSaveRequest request) {
        return fileInfoRepository.save(request.toEntity());
    }

}
