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
@Table(name = "map_tb")
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


    @Comment("맵 URL")
    private String mapImageUrl;

    private double lat;

    private double lng;

    private Integer zoom;
}
