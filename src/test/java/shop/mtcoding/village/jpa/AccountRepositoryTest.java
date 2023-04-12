package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp(4L, "45456451651651");
    }

    @Test
    @Transactional
    void selectAll() {
        List<Account> accounts = accountRepository.findAll();
        Assertions.assertNotEquals(accounts.size(), 0);

        Account account = accounts.get(0);
        Assertions.assertEquals(account.getUserId(), 4L);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalAccount = this.accountRepository.findById(4L);

        if(optionalAccount.isPresent()) {
            var result = optionalAccount.get();
            Assertions.assertEquals(result.getUserId(),4L);

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
    void insertAndDelete() {
        Account account = setUp(5L, "84845161515165");
        Optional<Account> findAccount = this.accountRepository.findById(account.getId());

        if(findAccount.isPresent()) {
            var result = findAccount.get();
            Assertions.assertEquals(result.getUserId(),5L);
            entityManager.remove(account);
            Optional<Account> deleteAccount = this.accountRepository.findById(account.getId());
            if (deleteAccount.isPresent()) {
                Assertions.assertNull(deleteAccount.get());
            }
        } else {
            Assertions.assertNotNull(findAccount.get());
        }
    }

    private Account setUp(Long userId, String accountNum) {
        Account account = new Account();
        account.setUserId(userId);
        account.setAccountNum(accountNum);
        return this.entityManager.persist(account);
    }

}
