package shop.mtcoding.village.controller.file;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.s3.S3Service;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.file.dto.FileDTO;
import shop.mtcoding.village.dto.file.request.FileSaveRequest;
import shop.mtcoding.village.dto.file.request.FileUpdateRequest;
import shop.mtcoding.village.dto.file.response.FileResponse;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.notFoundConst.FileConst;
import shop.mtcoding.village.service.FileService;
import shop.mtcoding.village.util.Base64Decoded;

import javax.validation.Valid;
import java.io.IOException;
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

    @PostMapping("/file")
    public ResponseEntity<FileResponse> saveFile (
            @Valid @RequestBody FileSaveRequest request

    ) {

        var file = fileService.save(request);
        return ResponseEntity.ok(file.toResponse());
    }

    @PostMapping("/file/gallery")
    public ResponseEntity execWrite(@RequestBody FileSaveRequest fileSaveRequest) throws IOException {

        String imgPath = s3Service.upload(Base64Decoded.convertBase64ToMultipartFile(fileSaveRequest.getFileUrl()));

        fileSaveRequest.setFileUrl(imgPath);

        File save = fileService.save(fileSaveRequest);

        return new ResponseEntity(new ResponseDTO<>(1, 200, "s3에 등록 완료", save), HttpStatus.OK);
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
