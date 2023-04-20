package shop.mtcoding.village.dto.date.request;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.date.Dates;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class DateSaveDTO {

    @ElementCollection
    @Column(name = "dayOfWeekName")
    private List<String> dayOfWeekName;

    public Dates toEntity() {
        Dates date = new Dates();
        date.setDayOfWeekName(dayOfWeekName);
        return date;
    }

//    public Dates toEntity() {
//
//        Dates date = new Dates();
//        List<String> dates = new ArrayList<>();
//        String[] dayOfWeeks = {""};
//        for (String dayOfWeekName : dayOfWeeks) {
//            dates.add(dayOfWeekName);
//        }
//        date.setDayOfWeekName(dates);
//        return new Dates(dates, null);
//    }
}


