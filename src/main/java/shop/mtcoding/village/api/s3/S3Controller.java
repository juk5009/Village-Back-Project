package shop.mtcoding.village.api.s3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

