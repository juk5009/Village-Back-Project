package shop.mtcoding.village.model.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "search_tb")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("검색 아이디")
    private Long id;

    @Comment("유저 아이디")
    @ManyToOne
    private User user;

    @Comment("검색 키워드")
    private String keyword;
}
