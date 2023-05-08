package shop.mtcoding.village.controller.map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

    private final RestTemplate restTemplate;



    @GetMapping("/imageUrl")
    public ResponseEntity<Map<String, String>> getMapImageUrl(@RequestParam double lat, @RequestParam double lng, @RequestParam int zoom,
                                                              @RequestParam(required = false) String size, @RequestParam(required = false) String marker) {

        // Google Maps Static API endpoint
        String url = "https://maps.googleapis.com/maps/api/staticmap";

        // 요청 파라미터
        String parameters = String.format("center=%f,%f&zoom=%d&size=%s&markers=color:red%%7Clabel:S%%7C%f,%f", lat, lng, zoom, size != null ? size : "400x400", lat, lng);

        // 요청 URL 생성
        String requestUrl = url + "?" + parameters;

        // HTTP 요청 보내기 전 RequestEntity 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> request = new HttpEntity<>(headers);

        // RestTemplate을 이용한 HTTP 요청
        ResponseEntity<Map> response = restTemplate.exchange(requestUrl, HttpMethod.GET, request, Map.class);
        Map<String, String> responseBody = response.getBody();

        // JSON 형태의 응답 생성
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("imageUrl", responseBody.get("imageUrl"));

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}