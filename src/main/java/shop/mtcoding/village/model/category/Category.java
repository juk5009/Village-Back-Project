package shop.mtcoding.village.model.category;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카테고리 아이디")
    private Long id;

    @Comment("카테고리 이름")
    @Column(name = "category_name")
    private String categoryName;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Place place;

    @Builder
    public Category(String categoryName, Place place) {
        this.categoryName = categoryName;
        this.place = place;
    }
}
