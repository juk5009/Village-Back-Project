package shop.mtcoding.village.model.reservation;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.core.jpa.BaseTime;
import shop.mtcoding.village.dto.reservation.ReservationDTO;
import shop.mtcoding.village.dto.reservation.response.ReservationSaveResponse;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.TotalPrice;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "reservation_tb")
public class Reservation {

<<<<<<< HEAD
=======

    @Comment("예약 상태")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;


>>>>>>> cb21803 (Reservation save 완료)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 아이디")
    private Long id;

<<<<<<< HEAD
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)

=======
    @ManyToOne(cascade = CascadeType.PERSIST)
>>>>>>> cb21803 (Reservation save 완료)
    @Comment("예약한 유저 정보")
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
<<<<<<< HEAD
=======


>>>>>>> cb21803 (Reservation save 완료)

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

<<<<<<< HEAD

    public Reservation(User user, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {

=======
    public Reservation(User user, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum) {
>>>>>>> cb21803 (Reservation save 완료)
        this.user = user;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.peopleNum = peopleNum;
<<<<<<< HEAD
        this.status = status;
=======
>>>>>>> cb21803 (Reservation save 완료)
    }

    public ReservationSaveResponse toResponse() {
        User userName = new User();
        userName.setName(user.getName());

<<<<<<< HEAD
        return new ReservationSaveResponse(userName, peopleNum, date, startTime, endTime, status);
    }
//
//    public ReservationDTO toDTOResponse() {
//
//        User userBuild = User.builder().build();
//
//        Place placeBuild = Place.builder().build();
//
//        Integer totalPrice = TotalPrice.calculateTotalPrice(Reservation.builder().build());
//
//        return new ReservationDTO(userBuild, placeBuild, peopleNum, totalPrice, date, startTime, endTime);
//    }
//
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

=======
        return new ReservationSaveResponse(userName, peopleNum, date, startTime, endTime);
    }

    public List<ReservationDTO> toDTOResponse() {
        User userBuild = new User().builder().build();

        Place placeBuild = new Place().builder().build();

        Integer totalPrice = TotalPrice.calculateTotalPrice(Reservation.builder().build());

        return new List<ReservationDTO>(userBuild, placeBuild, peopleNum, totalPrice, date, startTime, endTime);
    }
>>>>>>> cb21803 (Reservation save 완료)

}
