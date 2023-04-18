package shop.mtcoding.village.dto.date.response;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class DateSaveResponse {

    private String dayOfWeekName;

    public DateSaveResponse(String dayOfWeekName) {
        this.dayOfWeekName = dayOfWeekName;
    }
}
