package shop.mtcoding.village.dto.reservation.request;

<<<<<<< HEAD

import lombok.*;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
=======
import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
>>>>>>> cb21803 (Reservation save 완료)

@Getter
@ToString
public class ReservationSaveRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    private Integer peopleNum;

<<<<<<< HEAD
    @NotNull(message = "날짜를 선택해주세요.")
    private LocalDateTime date;

    @NotNull(message = "시작 시간을 입력해주세요.")
    private LocalDateTime startTime;

    @NotNull(message = "끝 시간을 입력해주세요.")
    private LocalDateTime endTime;

    private ReservationStatus reservationStatus;

    public LocalTime getStartTime() {
        return startTime.toLocalTime();
    }

    public LocalTime getEndTime() {
        return endTime.toLocalTime();
    }


    public Reservation toEntity() {
        User user = new User();
        user.setName(userName);
=======
//    @NotBlank(message = "날짜를 선택해주세요.")
    private LocalDateTime date;

//    @NotBlank(message = "시작 시간을 입력해주세요.")
    private LocalDateTime startTime;

//    @NotBlank(message = "끝 시간을 입력해주세요.")
    private LocalDateTime endTime;

    public Reservation toEntity() {
        User user = new User();
        user.setName(userName);

>>>>>>> cb21803 (Reservation save 완료)
        Reservation reservation = new Reservation();
        reservation.setPeopleNum(peopleNum);
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
<<<<<<< HEAD
        reservation.setStatus(reservationStatus);
        return new Reservation(user, date, startTime, endTime, peopleNum, reservationStatus);

=======
        return new Reservation(user, date, startTime, endTime, peopleNum);
>>>>>>> cb21803 (Reservation save 완료)
    }

}
