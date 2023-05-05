package shop.mtcoding.village.model.facilityInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "facility_info_tb")
public class FacilityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("편의시설 아이디")
    @JsonIgnore
    private Long id;

    @Comment("편의시설 이름")
//    @ElementCollection
//    @CollectionTable(name = "facility_name", joinColumns = @JoinColumn(name = "facility_id"))
    @Column(name = "facility_name")
    private String facilityName;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Place place;

    @Builder
    public FacilityInfo(String facilityName, Place place) {
        this.facilityName = facilityName;
        this.place = place;
    }
}
