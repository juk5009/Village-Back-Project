package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.dto.imagemap.ImageMapSaveRequest;
import shop.mtcoding.village.model.map.ImageMap;
import shop.mtcoding.village.model.map.MapRepository;



@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    @Transactional
    public ImageMap saveMapUrl(ImageMapSaveRequest imageMapSaveRequest) {
        // Google Maps Static API endpoint
        String url = "https://maps.googleapis.com/maps/api/staticmap";

        // 요청 파라미터
        String parameters = String.format(
                "center=%f,%f&zoom=%d&size=400x400&markers=color:red%%7Clabel:%%7C%f,%f&key=%s",
                imageMapSaveRequest.getLat(), imageMapSaveRequest.getLng(), imageMapSaveRequest.getZoom(),
                imageMapSaveRequest.getLat(), imageMapSaveRequest.getLng(), "AIzaSyDmvb-5cgAOEvdoYoPt0jDUmxLpsW5aNvg" //api키
        );

        // 요청 URL 생성
        String requestUrl = url + "?" + parameters;

        ImageMap imageMap = imageMapSaveRequest.toEntity(requestUrl);

        return mapRepository.save(imageMap);
    }
}
