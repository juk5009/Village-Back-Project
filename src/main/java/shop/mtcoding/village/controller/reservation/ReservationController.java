package shop.mtcoding.village.controller.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.reservation.ReservationDTO;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.service.ReservationService;

import javax.validation.Valid;
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

    @GetMapping
    public ResponseEntity<?> getReservation(ReservationDTO reservationDTO){

        List<Reservation> allReservation = reservationRepository.findAll();

        List<ReservationDTO> allReservationDTO = allReservation.stream()
                .map(reservation -> new ModelMapper().map(reservation, ReservationDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResponseDTO<>(1, "예약내역 조회완료",allReservationDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        Optional<Reservation> userId = reservationRepository.findById(id);

        return new ResponseEntity<>(new ResponseDTO<>(1, "유저 예약내역 조회완료", userId), HttpStatus.OK);
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
}
