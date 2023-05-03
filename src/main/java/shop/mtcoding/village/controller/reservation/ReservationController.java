package shop.mtcoding.village.controller.reservation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.mtcoding.village.api.firebase.FirebaseCloudMessageService;
import shop.mtcoding.village.api.firebase.RequestDTO;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.reservation.ReservationDTO;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.dto.reservation.response.ReservationSaveResponse;
import shop.mtcoding.village.model.fcm.Fcm;
import shop.mtcoding.village.model.fcm.FcmRepository;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceJpaRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.notFoundConst.ReservationConst;
import shop.mtcoding.village.service.ReservationService;
import shop.mtcoding.village.util.DateUtils;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationRepository reservationRepository;

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    private final PlaceJpaRepository placeJpaRepository;

    private final FcmRepository fcmRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO<List<Reservation>>> getReservation(){

        List<Reservation> allReservation = reservationRepository.findAll();

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "예약내역 조회완료", allReservation), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO<ReservationDTO>> getById(@PathVariable Long id) {

        Optional<Reservation> optionalUser = reservationRepository.findById(id);
        System.out.println("디버그 : " + optionalUser);

        if (optionalUser.isEmpty()) {
            throw new MyConstException(ReservationConst.notfound);
        }

        Reservation reservation = optionalUser.get();

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "유저 예약내역 조회완료", reservation.toDTOResponse()), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO<ReservationSaveResponse>> save(
            @Valid @RequestBody ReservationSaveRequest reservationSaveRequest,
            @AuthenticationPrincipal MyUserDetails myUserDetails
            ) throws IOException {

        var saveReservation = reservationService.예약신청(reservationSaveRequest);
        System.out.println("예약신청 : " +saveReservation);
//        Long placeId = saveReservation.getPlace().getId();
        Place byId = placeJpaRepository.findById(1L).get();

        LocalDate date = DateUtils.fromLocalDateTime(reservationSaveRequest.getDate());
        System.out.println(date); // 예시 출력: 2023-04-25

        Long userId = saveReservation.getUser().getId();
        Fcm fcm = fcmRepository.findByUserId(userId);

        RequestDTO requestDTO = new RequestDTO("Village",
                "[예약알림]\n"+ reservationSaveRequest.getUserName()+ "님이 [" + byId.getTitle() + "]에 예약 신청했습니다.\n"
                        +"날짜: "+date+"\n"
                        +"일시: "+reservationSaveRequest.getStartTime()+"~"+reservationSaveRequest.getEndTime()+"\n"
                        +"인원: "+reservationSaveRequest.getPeopleNum()+"명\n",
                fcm.getTargetToken());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "예약 신청 완료", saveReservation.toResponse()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        Optional<Reservation> reservation = reservationService.getReservation(id);
        if (reservation.isEmpty()) {
            throw new MyConstException(ReservationConst.notfound);
        }
        reservationService.예약삭제(id);
        return new ResponseEntity<>(new ResponseDTO<>(1, 200 , "예약내역 삭제완료", null), HttpStatus.OK);
    }


}
