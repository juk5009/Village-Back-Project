package shop.mtcoding.village.dto.date.request;

import shop.mtcoding.village.model.date.Dates;

import java.util.List;

public class DateSaveRequest {
    private String dayOfWeekName;

    public Dates toEntity() {
        return new Dates(dayOfWeekName, null);
    }
}


