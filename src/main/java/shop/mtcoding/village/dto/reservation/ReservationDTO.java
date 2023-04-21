package shop.mtcoding.village.dto.reservation;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;

@Getter
@ToString
public class ReservationDTO {

    private User user;

    private Place place;

    private Integer peopleNum;

    private Integer totalPrice;

    private LocalDateTime date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    public ReservationDTO() {

    }


    public ReservationDTO(User user, Place place, Integer peopleNum, Integer totalPrice, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.place = place;
        this.peopleNum = peopleNum;
        this.totalPrice = totalPrice;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

