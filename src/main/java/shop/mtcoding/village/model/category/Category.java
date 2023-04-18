package shop.mtcoding.village.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;

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
    @JsonIgnore
    private Long id;

    @Comment("카테고리 이름")
    // 연습실, 스터디룸, 공유오피스
    private String name;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}
