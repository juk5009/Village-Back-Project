package shop.mtcoding.village.model.search;

import lombok.*;
import org.hibernate.annotations.Comment;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "search_tb")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("검색 아이디")
    private Long id;

    @Comment("유저 아이디")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "place_id")
    private Place place;

    @Comment("검색 키워드")
    private String keyword;

    @Builder
    public Search(User user, String keyword) {
        this.user = user;
        this.keyword = keyword;
    }




}
