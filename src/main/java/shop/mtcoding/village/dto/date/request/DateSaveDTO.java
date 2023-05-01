package shop.mtcoding.village.dto.date.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class DateSaveDTO {

//    @Column(name = "dayOfWeekName")
//    private List<DatesDto> dayOfWeekName;

    @Setter
    @Getter
    @ToString
    public static class DateSaveDto extends Dates {
        private String dayOfWeekName;
        private Place placeId;

        public Dates toEntity(String name, Place id) {
            Dates dayofName = new Dates();
            dayofName.setPlace(id);
            dayofName.setDayOfWeekName(name);
            return dayofName;
        }
    }
}


