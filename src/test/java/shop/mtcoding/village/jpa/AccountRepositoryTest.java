package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.account.Account;
import shop.mtcoding.village.model.account.AccountRepository;
import shop.mtcoding.village.model.chatRoom.ChatRoom;
import shop.mtcoding.village.model.chatRoom.ChatRoomRepository;
import shop.mtcoding.village.model.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("계좌 JPA 테스트")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

//    @BeforeEach
//    public void init() {
//        setUp("45456451651651");
//    }

    @Test
    @Transactional
    @DisplayName("계좌 조회 테스트")
    void selectAll() {
        List<Account> accounts = accountRepository.findAll();
        Assertions.assertNotEquals(accounts.size(), 0);

        Account account = accounts.get(0);
        Assertions.assertEquals(account.getUser().getName(), "ssar");
    }

    @Test
    @Transactional
    @DisplayName("계좌 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalAccount = this.accountRepository.findById(1L);

        if(optionalAccount.isPresent()) {
            var result = optionalAccount.get();
            Assertions.assertEquals(result.getUser().getName(), "ssar");

            var accountNum = "4516515161516";
            result.setAccountNum(accountNum);
            Account merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getAccountNum(), "4516515161516");
        } else {
            Assertions.assertNotNull(optionalAccount.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("계좌 삽입 및 삭제 테스트")
    void insertAndDelete() {
        Account account = setUp("84845161515165");
        Optional<Account> findAccount = this.accountRepository.findById(account.getId());

        if(findAccount.isPresent()) {
            var result = findAccount.get();
            Assertions.assertEquals(result.getUser().getName(), "love");
            entityManager.remove(account);
            Optional<Account> deleteAccount = this.accountRepository.findById(account.getId());
            if (deleteAccount.isPresent()) {
                Assertions.assertNull(deleteAccount.get());
            }
        } else {
            Assertions.assertNotNull(findAccount.get());
        }
    }

    private Account setUp(String accountNum) {
        User user = new User().builder().name("love").password("1234").email("sar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);
        Account account = new Account();
        account.setUser(user);
        account.setAccountNum(accountNum);
        return this.entityManager.persist(account);
    }

}
