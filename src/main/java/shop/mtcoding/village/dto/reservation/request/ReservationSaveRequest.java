package shop.mtcoding.village.dto.reservation.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.DateUtils;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSaveRequest {
    private Long placeId;

    private Integer peopleNum;

    @NotNull(message = "날짜를 선택해주세요.")
    private String date;

    @NotNull(message = "시작 시간을 입력해주세요.")
    private String startTime;

    @NotNull(message = "끝 시간을 입력해주세요.")
    private String endTime;

    private ReservationStatus reservationStatus;


    public Reservation toEntity(User user) {

        LocalDateTime dateParser = DateUtils.parseLocalDateTime(date);
        LocalDateTime startTimeParser = DateUtils.parseLocalDateTime(startTime);
        LocalDateTime endTimeParser = DateUtils.parseLocalDateTime(endTime);

        Reservation reservation = new Reservation();
        reservation.setPeopleNum(peopleNum);
        reservation.setDate(dateParser);
        reservation.setStartTime(startTimeParser);
        reservation.setEndTime(endTimeParser);
        reservation.setStatus(reservationStatus);
        return new Reservation(user, dateParser, startTimeParser, endTimeParser, peopleNum, reservationStatus);

    }

}
