package shop.mtcoding.village.model.reservation;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.dto.place.PlaceDTO;
import shop.mtcoding.village.dto.reservation.ReservationDTO;
import shop.mtcoding.village.dto.reservation.response.ReservationSaveResponse;
import shop.mtcoding.village.dto.user.UserDTO;
import shop.mtcoding.village.dto.user.UserReservationDTO;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.TotalPrice;
import shop.mtcoding.village.util.status.ReservationStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "reservation_tb")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 아이디")
    private Long id;

    // TODO hibernateLazyInitializer 이친구 알아보기
    @ManyToOne(fetch = FetchType.EAGER)
    @Comment("예약한 유저 정보")
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;

    @Comment("예약 날짜")
    private LocalDateTime date;

    @Comment("예약 시작 시간")
    private LocalDateTime startTime;

    @Comment("예약 마감 시간")
    private LocalDateTime endTime;

    @Comment("예약 인원수")
    private Integer peopleNum;

    @Comment("예약 상태")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Builder
    public Reservation(User user, Place place, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {
        this.user = user;
        this.place = place;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.peopleNum = peopleNum;
        this.status = status;
    }


    public Reservation(User user, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {

        this.user = user;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.peopleNum = peopleNum;
        this.status = status;
    }
    public ReservationSaveResponse toResponse(User user, Place place) {
        UserReservationDTO userDTO = new UserReservationDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());

        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setTitle(place.getTitle());
        placeDTO.setAddress(place.getAddress());
        return new ReservationSaveResponse(id, userDTO, placeDTO, peopleNum, date, startTime, endTime, status);
    }
    
    public ReservationDTO toDTOResponse() {
        Integer totalPrice = TotalPrice.calculateTotalPrice(this);
        System.out.println("디버그 : " + totalPrice);
        return new ReservationDTO(
                this.getUser(),
                this.getPlace(),
                this.getPeopleNum(),
                totalPrice,
                this.getDate(),
                this.getStartTime(),
                this.getEndTime()
        );

    }


}
