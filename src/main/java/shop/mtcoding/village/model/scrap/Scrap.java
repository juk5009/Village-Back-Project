package shop.mtcoding.village.model.scrap;


import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "scrap_tb")
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스크랩 아이디")
    private Long id;

    @Comment("유저 아이디")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @Comment("공간 아이디")
    @JoinColumn(name = "place_id")
    private Place place;

    @Comment("스크랩 수")
    private int count;

    @Builder
    public Scrap(User user, Place place, int count) {
        this.user = user;
        this.place = place;
        this.count = count;
    }
}
