package shop.mtcoding.village.model.scrap;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

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
    private Long userId;
    @Comment("공간 아이디")
    private Long placeId;
    @Comment("스크랩 수")
    private int count;
}
