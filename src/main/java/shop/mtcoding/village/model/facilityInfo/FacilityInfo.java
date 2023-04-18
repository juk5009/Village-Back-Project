package shop.mtcoding.village.model.facilityInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

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
}
