package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.validation.Valid;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation 예약신청(ReservationSaveRequest reservationSaveRequest, User user) {

        try {
            reservationSaveRequest.setReservationStatus(ReservationStatus.WAIT);
            return reservationRepository.save(reservationSaveRequest.toEntity(user));
        } catch (Exception500 e) {
            throw new Exception500("예약신청 오류" + e.getMessage());
        }
    }

    @Transactional
    public void 예약삭제(Long id) {
        reservationRepository.deleteById(id);
    }

    @Transactional
    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }
}
