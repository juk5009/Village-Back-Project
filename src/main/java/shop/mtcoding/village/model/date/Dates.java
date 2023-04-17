package shop.mtcoding.village.model.date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.dto.date.DateSaveResponse;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "dates_tb")
public class Dates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("날짜 아이디")
    @JsonIgnore
    private Long id;

    @Comment("요일")
    private String dayOfWeekName;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId")
    @JsonIgnore
    private Place placeId;


    @Builder
    public Dates(List<String> dayOfWeekName, Place placeId) {
        this.dayOfWeekName = dayOfWeekName.toString();
        this.placeId = placeId;
    }

    public DateSaveResponse toResponse() {
        return new DateSaveResponse(dayOfWeekName);
    }
}
