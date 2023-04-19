package shop.mtcoding.village.model.facilityInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "facility_info_tb")
public class FacilityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("편의시설 아이디")
    private Long id;

    @Comment("편의시설 이름")
    private String facilityName;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place placeId;

    @Builder
    public FacilityInfo(String facilityName, Place placeId) {
        this.facilityName = facilityName;
        this.placeId = placeId;
    }
}
