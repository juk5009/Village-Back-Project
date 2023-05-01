package shop.mtcoding.village.controller.file;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.api.s3.S3Service;
import shop.mtcoding.village.dto.file.dto.FileDTO;
import shop.mtcoding.village.dto.file.request.FileUpdateRequest;
import shop.mtcoding.village.dto.file.response.FileResponse;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.notFoundConst.FileConst;
import shop.mtcoding.village.service.FileService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class FileController {
    private final FileService fileService;
    
    private final S3Service s3Service;

    public FileController(FileService fileService, S3Service s3Service) {
        this.fileService = fileService;
        this.s3Service = s3Service;
    }

    @GetMapping("/file")
    public ResponseEntity<Page<FileDTO>> getPage(Pageable pageable) {
        var page = fileService.getPage(pageable);
        var content = page.getContent()
                .stream()
                .map(File::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new PageImpl<>(content, pageable, page.getTotalElements())
        );
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<FileResponse> getFile (@PathVariable Long id) {
        var optionalFile = fileService.getFile(id);
        if (optionalFile.isEmpty()) {
            throw new MyConstException(FileConst.notfound);
        }

        return ResponseEntity.ok(
                optionalFile.get().toResponse()
        );
    }

    @PutMapping("/file/{id}")
    public ResponseEntity<FileResponse> updateFile (
            @Valid @RequestBody FileUpdateRequest request,
            Errors error,
            @PathVariable Long id
    ) {

        var optionalFile = fileService.getFile(id);
        if (optionalFile.isEmpty()) {
            throw new MyConstException(FileConst.notfound);
        }

        var file = fileService.update(request, optionalFile.get());
        return ResponseEntity.ok(file.toResponse());
    }

}
