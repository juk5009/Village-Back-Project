package shop.mtcoding.village.controller.file;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.file.dto.FileDTO;
import shop.mtcoding.village.dto.file.request.FileSaveRequest;
import shop.mtcoding.village.dto.file.request.FileUpdateRequest;
import shop.mtcoding.village.dto.file.response.FileResponse;
import shop.mtcoding.village.model.file.File;
import shop.mtcoding.village.notFoundConst.FileConst;
import shop.mtcoding.village.service.FileService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<FileResponse> getFile (@PathVariable Long id) {
        var optionalFile = fileService.getFile(id);
        if (optionalFile.isEmpty()) {
            throw new MyConstException(FileConst.notfound);
        }

        return ResponseEntity.ok(
                optionalFile.get().toResponse()
        );
    }

    @PostMapping
    public ResponseEntity<FileResponse> saveFile (
            @Valid @RequestBody FileSaveRequest request

    ) {

        var file = fileService.save(request);
        return ResponseEntity.ok(file.toResponse());
    }

    @PutMapping("/{id}")
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
