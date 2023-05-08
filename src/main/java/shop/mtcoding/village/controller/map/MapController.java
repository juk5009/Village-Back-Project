package shop.mtcoding.village.controller.map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.imagemap.request.ImageMapSaveRequest;
import shop.mtcoding.village.model.map.ImageMap;
import shop.mtcoding.village.service.MapService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/maps")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @PostMapping()
    public ResponseEntity<ResponseDTO<?>> createMap(@RequestBody ImageMapSaveRequest imageMapSaveRequest) {
        ImageMap createdImageMap = mapService.saveMapUrl(imageMapSaveRequest);
        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(createdImageMap);

        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("/imageUrl") //쿼리스트링으로 받기
    public ResponseEntity<Map<String, String>> getMapImageUrl(
            @RequestParam double lat, @RequestParam double lng, @RequestParam int zoom
    ) {
        try {
            // Google Maps Static API endpoint
            String url = "https://maps.googleapis.com/maps/api/staticmap";

            // 요청 파라미터
            String parameters = String.format(
                    "center=%f,%f&zoom=%d&size=400x400&markers=color:red%%7Clabel:%%7C%f,%f&key=%s",
                    lat, lng, zoom, lat, lng, "AIzaSyDmvb-5cgAOEvdoYoPt0jDUmxLpsW5aNvg" //api키
            );

            // 요청 URL 생성
            String requestUrl = url + "?" + parameters;

            // JSON 형태의 응답 생성
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", requestUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}