package shop.mtcoding.village.api.firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.model.fcm.Fcm;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FcmController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

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

        token = "dVimDFTAQJCHMrFDJD2W18:APA91bFef_eC8HUP_PPjtGnt3_1hJR4m-BJMDr2PSfFqA9eNtnYh4XTOqCStmPKnWgv6XDCkzur7kCrxlvghvtTPttD58zYKrz8OhkZn8Pc40vO9YCRIpJhHPaMT3wEMEkF7l7TCZkDx";

        if (token != null) {

            Fcm fcm = new Fcm();

            fcm.setTargetToken(token);

            System.out.println("앱 실행 후 토큰 전송 성공!, token : " + token);
            // token 받기 성공
            // token 받고, DB에 저장 후 푸쉬 알림 시 활용
        } else {
            System.out.println("토큰 전송 실패!");
        }
        return ResponseEntity.ok().build();
    }


}
