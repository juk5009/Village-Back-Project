package shop.mtcoding.village.model.scrap;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scrap_tb")
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스크랩 아이디")
    private Long id;

    @Comment("유저 아이디")
    @ManyToOne
    private User user;

    @ManyToOne
    @Comment("공간 아이디")
    private Place place;

    @Comment("스크랩 수")
    private int count;
}
