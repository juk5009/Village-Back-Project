package shop.mtcoding.village.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.fcm.Fcm;
import shop.mtcoding.village.model.fcm.FcmRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation 예약신청(ReservationSaveRequest reservationSaveRequest) {

        try {
            return reservationRepository.save(reservationSaveRequest.toEntity());
        } catch (Exception500 e) {
            throw new Exception500("예약신청 오류" + e.getMessage());
        }
    }

    @Transactional
    public void 예약삭제(Long id) {
//        reservationRepository.delete(reservation);
        reservationRepository.deleteById(id);
    }

    @Transactional
    public Optional<Reservation> getReservation(Long id) {
        return reservationRepository.findById(id);
    }
}
