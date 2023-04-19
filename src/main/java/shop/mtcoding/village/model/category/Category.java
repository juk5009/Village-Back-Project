package shop.mtcoding.village.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("카테고리 아이디")
    private Long id;

    @Comment("카테고리 이름")
    // 연습실, 스터디룸, 공유오피스
    private String categoryName;

//    @Comment("공간의 아이디")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "place_id")
//    private Place placeId;

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;

    }
}
