package shop.mtcoding.village.model.map;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "imagemap_tb")
public class ImageMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("맵 아이디")
    private Long id;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place place;

    @Comment("위도")
    private double lat;


    @Comment("경도")
    private double lng;


    @Comment("줌")
    private Integer zoom;


    @Comment("맵 URL")
    private String mapImageUrl;
}
