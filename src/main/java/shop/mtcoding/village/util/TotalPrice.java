package shop.mtcoding.village.util;

import shop.mtcoding.village.model.reservation.Reservation;

import java.time.Duration;

public class TotalPrice {
    public static int calculateTotalPrice(Reservation reservation) {

        // 예약 시작 시간과 끝 시간으로 예약 기간을 계산.
        Duration duration = Duration.between(reservation.getStartTime(), reservation.getEndTime());
        long hours = duration.toHours();

        // 장소의 1시간당 가격을 조회.
        int pricePerHour = reservation.getPlace().getPricePerHour();

        // 총 가격을 계산.

        int totalPrice = pricePerHour * (int)hours;

        return totalPrice;
    }

}
