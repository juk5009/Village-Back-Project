package shop.mtcoding.village.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.file.dto.FileSaveDTO;
import shop.mtcoding.village.dto.file.request.FileUpdateRequest;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.model.file.FileRepository;
import shop.mtcoding.village.model.file.FileStatus;

import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String utf(String url){
        return url;
    }

    public Page<File> getPage(Pageable pageable) {
        return fileRepository.findAll(pageable);
    }

    public Optional<File> getFile(Long id) {
        return fileRepository.findById(id);
    }

    @Transactional
    public File save(FileSaveDTO.FileSaveDto request) {
        return fileRepository.save(request.toEntity());
    }

    public File update(FileUpdateRequest request, File file) {
        file.setFileUrl(request.getFileUrl());
        file.setFileName(request.getFileName());
        file.setStatus(FileStatus.valueOf(request.getStatus()));
        return fileRepository.save(file);
    }

    @Transactional
    public void delete(File file) {
        fileRepository.delete(file);
    }
}
