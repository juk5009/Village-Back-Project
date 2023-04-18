package shop.mtcoding.village.model.facilityInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "facilityInfo_tb")
public class FacilityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("편의시설 아이디")
    private Long id;

    @Comment("편의시설 이름")
    private String facilityName;

    @Comment("공간의 아이디")
    private Long placeId;

    @Builder
    public FacilityInfo(String facilityName, Long placeId) {
        this.facilityName = facilityName;
        this.placeId = placeId;
    }
}
