package shop.mtcoding.village.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static LocalDate parseDateString(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString, FORMATTER);
        return dateTime.toLocalDate();
    }

    public static LocalDate fromLocalDateTime(LocalDateTime dateTime) {
        return LocalDate.from(dateTime);
    }
}