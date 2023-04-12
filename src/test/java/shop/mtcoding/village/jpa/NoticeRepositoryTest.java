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
import shop.mtcoding.village.model.notice.Notice;
import shop.mtcoding.village.model.notice.NoticeRepository;
import shop.mtcoding.village.util.status.NoticeStatus;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp(4L, 4L, 40000,"내용4", NoticeStatus.WAIT);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Notice> notices = noticeRepository.findAll();
        Assertions.assertNotEquals(notices.size(), 0);

        Notice notice = notices.get(0);
        Assertions.assertEquals(notice.getUserId(), 4L);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalNotice = this.noticeRepository.findById(4L);

        if(optionalNotice.isPresent()) {
            var result = optionalNotice.get();
            Assertions.assertEquals(result.getUserId(),4L);

            var paymentTotalPrice = 50000;
            result.setPaymentTotalPrice(paymentTotalPrice);
            Notice merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getPaymentTotalPrice(), 50000);
        } else {
            Assertions.assertNotNull(optionalNotice.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Notice notice = setUp(5L, 5L,80000, "내용5", NoticeStatus.WAIT);
        Optional<Notice> findNotice = this.noticeRepository.findById(notice.getId());

        if(findNotice.isPresent()) {
            var result = findNotice.get();
            Assertions.assertEquals(result.getUserId(),5L);
            entityManager.remove(notice);
            Optional<Notice> deleteAccount = this.noticeRepository.findById(notice.getId());
            if (deleteAccount.isPresent()) {
                Assertions.assertNull(deleteAccount.get());
            }
        } else {
            Assertions.assertNotNull(findNotice.get());
        }
    }

    private Notice setUp(Long userId, Long placeId, Integer paymentTotalPrice, String content, NoticeStatus status) {
        Notice notice = new Notice();
        notice.setUserId(userId);
        notice.setPlaceId(placeId);
        notice.setPaymentTotalPrice(paymentTotalPrice);
        notice.setContent(content);
        notice.setStatus(status);
        return this.entityManager.persist(notice);
    }
}
