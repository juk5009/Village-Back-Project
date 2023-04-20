package shop.mtcoding.village.dto.reservation.response;

import lombok.Getter;
import shop.mtcoding.village.model.user.User;
<<<<<<< HEAD
import shop.mtcoding.village.util.status.ReservationStatus;


=======

import javax.validation.constraints.NotBlank;
>>>>>>> cb21803 (Reservation save 완료)
import java.time.LocalDateTime;

@Getter
public class ReservationSaveResponse {

    private User user;

    private Integer peopleNum;

    private LocalDateTime date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

<<<<<<< HEAD
    private ReservationStatus status;

    public ReservationSaveResponse(User user, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status) {

=======
    public ReservationSaveResponse(User user, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime) {
>>>>>>> cb21803 (Reservation save 완료)
        this.user = user;
        this.peopleNum = peopleNum;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
<<<<<<< HEAD
        this.status = status;

=======
>>>>>>> cb21803 (Reservation save 완료)
    }
}
