package shop.mtcoding.village.controller.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.core.firebase.FirebaseCloudMessageService;
import shop.mtcoding.village.core.firebase.RequestDTO;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.reservation.ReservationDTO;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.ReservationService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationRepository reservationRepository;

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @GetMapping
    public ResponseEntity<?> getReservation(){

        List<Reservation> allReservation = reservationRepository.findAll();

        List<ReservationDTO> allReservationDTO = allReservation.stream()
                .map(reservation -> new ModelMapper().map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResponseDTO<>(1, "예약내역 조회완료",allReservation), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Optional<Reservation> optionalUser = reservationRepository.findById(id);
        System.out.println("디버그 : " + optionalUser);

        if (!optionalUser.isPresent()) {
            throw new MyConstException(ReservationConst.notFound);
        }

        return new ResponseEntity<>(new ResponseDTO<>(1, "유저 예약내역 조회완료", optionalUser.get().toDTOResponse()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(
            @Valid @RequestBody ReservationSaveRequest reservationSaveRequest, BindingResult result
            ) {

        if (result.hasErrors()) {
            throw new Exception400(result.getAllErrors().get(0).getDefaultMessage());
        }

        var saveReservation = reservationService.예약신청(reservationSaveRequest);

        return new ResponseEntity<>(new ResponseDTO<>(1, "예약 신청 완료", saveReservation.toResponse()), HttpStatus.OK);
    }

//    // 푸쉬 알림 보내는 핸들러
//    @PostMapping("/api/fcm")
//    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
//        System.out.println(requestDTO.getTargetToken() + " "
//                + requestDTO.getTitle() + " " + requestDTO.getBody());
//
//        firebaseCloudMessageService.sendMessageTo(
//                requestDTO.getTargetToken(),
//                requestDTO.getTitle(),
//                requestDTO.getBody());
//        return ResponseEntity.ok().build();
//    }
//
//    // 앱 실행 후 토큰 보냄 -> server에서 받는 핸들러
//    @PostMapping("/fcm/token")
//    public ResponseEntity<?> pushMessage(@RequestBody String token) throws Exception {
//        if (token != null) {
//            System.out.println("앱 실행 후 토큰 전송 성공!, token : " + token);
//            // token 받기 성공
//            // token 받고, DB에 저장 후 푸쉬 알림 시 활용
//        } else {
//            System.out.println("토큰 전송 실패!");
//        }
//        return ResponseEntity.ok().build();
//    }
}
