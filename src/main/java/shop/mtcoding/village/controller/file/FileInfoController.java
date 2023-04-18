package shop.mtcoding.village.controller.file;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.file.dto.FileInfoDTO;
import shop.mtcoding.village.dto.file.request.FileInfoSaveRequest;
import shop.mtcoding.village.dto.file.response.FileInfoResponse;
import shop.mtcoding.village.notFoundConst.FileInfoConst;
import shop.mtcoding.village.service.FileInfoService;
import shop.mtcoding.village.model.file.FileInfo;

import javax.validation.Valid;

@RestController
@RequestMapping("/fileInfo")
public class FileInfoController {

    private final FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @GetMapping
    public ResponseEntity<Page<FileInfoDTO>> getPage(Pageable pageable) {
        var page = fileInfoService.getPage(pageable);
        var content = page.getContent()
                .stream()
                .map(FileInfo::toDTO)
                .toList();

        return ResponseEntity.ok(
                new PageImpl<>(content, pageable, page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileInfoResponse> getFileInfo (@PathVariable Long id) {
        var optionalFileInfo = fileInfoService.getFileInfo(id);
        if (optionalFileInfo.isEmpty()) {
            throw new Exception400(FileInfoConst.notfound);
        }

        return ResponseEntity.ok(optionalFileInfo.get().toResponse());
    }

    @PostMapping
    public ResponseEntity<FileInfoResponse> saveFileInfo (
            @Valid @RequestBody FileInfoSaveRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        var fileInfo = fileInfoService.save(request);
        return ResponseEntity.ok(fileInfo.toResponse());
    }
}
