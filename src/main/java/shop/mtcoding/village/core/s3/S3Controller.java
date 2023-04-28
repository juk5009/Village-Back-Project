package shop.mtcoding.village.core.s3;

import io.netty.handler.codec.base64.Base64Decoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.village.util.Base64Decoded;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;
    private final GalleryService galleryService;


    public S3Controller(S3Service s3Service, GalleryService galleryService) {
        this.s3Service = s3Service;
        this.galleryService = galleryService;
    }

    @GetMapping("/gallery")
    public String dispWrite() {

        return "/gallery";
    }

    @PostMapping("/gallery")
    public String execWrite(@RequestBody GalleryDto galleryDto) throws IOException {

        String imgPath = s3Service.upload(Base64Decoded.convertBase64ToMultipartFile(galleryDto.getFile()));

        galleryDto.setFilePath(imgPath);

        galleryService.savePost(galleryDto);

        return "redirect:/gallery";
    }
}

