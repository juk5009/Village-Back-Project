package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.search.Search;
import shop.mtcoding.village.model.search.SearchJpaRepository;
import shop.mtcoding.village.model.user.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("검색 JPA 테스트")
public class SearchRepositoryTest {

    @Autowired
    private SearchJpaRepository searchRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;


//    @BeforeEach
//    public void init() {
//        em.createNativeQuery("ALTER TABLE search_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
//
//        setUpBySearch("keyword4");
//    }

    @Test
    @Transactional
    @DisplayName("검색 조회 테스트")
    void selectAll() {
        List<Search> searches = searchRepository.findAll();
        Assertions.assertNotEquals(searches.size(), 0);

        Search search = searches.get(0);
        Assertions.assertEquals(search.getKeyword(), "연습실");
    }

    @Test
    @Transactional
    @DisplayName("검색 조회 및 수정 테스트")
    void selectAndUpdate() {
        var optionalSearch = this.searchRepository.findById(1L);

        if(optionalSearch.isPresent()) {
            var result = optionalSearch.get();
            Assertions.assertEquals(result.getKeyword(), "연습실");

            var keyword = "keyword55";
            result.setKeyword(keyword);
            Search merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getKeyword(), "keyword55");
        } else {
            Assertions.assertNotNull(optionalSearch.get());
        }
    }

    @Test
    @Transactional
    @DisplayName("검색 삽입 및 삭제 테스트")
    void insertAndDelete() {
//        User user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");

        Search search = setUpBySearch("keyword4");
        Optional<Search> findUser = this.searchRepository.findById(search.getId());

        if(findUser.isPresent()) {
            var result = findUser.get();
            Assertions.assertEquals(result.getKeyword(),"keyword4");
            entityManager.remove(search);
            Optional<Search> deleteSearch = this.searchRepository.findById(search.getId());
            if (deleteSearch.isPresent()) {
                Assertions.assertNull(deleteSearch.get());
            }
        } else {
            Assertions.assertNotNull(findUser.get());
        }
    }

    private Search setUpBySearch(String keyword) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Search search = new Search(user, keyword);
        search.setUser(user);
        search.setKeyword(keyword);
        return this.entityManager.persist(search);
    }
}
