package shop.mtcoding.village.core.s3;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@AllArgsConstructor
public class GalleryService {
    private GalleryRepository galleryRepository;

    public void savePost(GalleryDto galleryDto) throws IOException {
        System.out.println("디버그 : " + galleryDto);

        Gallery save = galleryRepository.save(galleryDto.toEntity());
        System.out.println("디버그 : " + save);
    }
}