package shop.mtcoding.village.model.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation_tb")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 아이디")
    private Long id;

    @ManyToOne
    @Comment("예약한 유저 정보")
    private User user;
    @Comment("공간 정보")
    @ManyToOne
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
    private ReservationStatus status;
}
