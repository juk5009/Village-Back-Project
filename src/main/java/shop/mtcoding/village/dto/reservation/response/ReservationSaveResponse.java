package shop.mtcoding.village.dto.reservation.response;

import lombok.Getter;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;


import java.time.LocalDateTime;

@Getter
public class ReservationSaveResponse {

    private User user;

    private Integer peopleNum;

    private LocalDateTime date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReservationStatus status;

    public ReservationSaveResponse(User user, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status) {

        this.user = user;
        this.peopleNum = peopleNum;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;

    }
}
