package shop.mtcoding.village.dto.reservation.response;

import lombok.Getter;
import shop.mtcoding.village.dto.place.PlaceDTO;
import shop.mtcoding.village.dto.user.UserDTO;
import shop.mtcoding.village.dto.user.UserReservationDTO;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class ReservationSaveResponse {

    private Long id;

    private Integer peopleNum;

    private LocalDateTime date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ReservationStatus status;

    private UserReservationDTO user;

    private PlaceDTO place;


    public ReservationSaveResponse(Long id, UserReservationDTO user, PlaceDTO place, Integer peopleNum, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status) {
        this.id = id;
        this.user = user;
        this.place = place;
        this.peopleNum = peopleNum;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;

    }
}
