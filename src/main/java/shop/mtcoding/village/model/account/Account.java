package shop.mtcoding.village.model.account;

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
@Table(name = "account_tb")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("계좌 아이디")
    private Long id;

    @Comment("계좌 유저아이디")
    @ManyToOne
    private User user;

    @Comment("계좌번호")
    private String accountNum;
}
