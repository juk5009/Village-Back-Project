package shop.mtcoding.village.api.firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.core.exception.CustomException;
import shop.mtcoding.village.model.fcm.Fcm;
import shop.mtcoding.village.model.fcm.FcmRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FcmController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    private final FcmRepository fcmRepository;

    // 푸쉬 알림 보내는 핸들러
    @PostMapping("/api/fcm")
    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {

        System.out.println(requestDTO.getTargetToken() + " "
                + requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }

    // 앱 실행 후 토큰 보냄 -> server에서 받는 핸들러
    @PostMapping("/fcm/token")
    public ResponseEntity<?> pushMessage(@RequestBody String token) throws Exception {

        if (token != null) {

            Fcm fcm = new Fcm();
            String targetToken = token.split("=")[1];

            Optional<Fcm> targetTokenOptional = fcmRepository.findByTargetToken(targetToken);

            if (targetTokenOptional.isEmpty()) {
                System.out.println("디버그 : 일단 if 문 들어옴");
                fcm.setTargetToken(targetToken);
                fcmRepository.save(fcm);
                System.out.println("디버그 : fcm에 토큰값 : " + fcm);

                // token 받기 성공
                // token 받고, DB에 저장 후 푸쉬 알림 시 활용
            }

            System.out.println("앱 실행 후 토큰 전송 성공!, token : " + token);
        } else {
            System.out.println("토큰 전송 실패!");
        }
        return ResponseEntity.ok().build();
    }


}
