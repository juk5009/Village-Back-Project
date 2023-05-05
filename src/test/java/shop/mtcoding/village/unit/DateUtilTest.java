package shop.mtcoding.village.unit;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilTest {

    @Test
    void parseLocalDateTimeTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime.parse("2023-03-03T15:12:22", formatter);
    }

}
