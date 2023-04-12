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
import shop.mtcoding.village.model.search.Search;
import shop.mtcoding.village.model.search.SearchRepository;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.model.user.UserRepository;

import javax.naming.directory.SearchControls;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class SearchRepositoryTest {

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp(4L, "keyword4");
    }

    @Test
    @Transactional
    void selectAll() {
        List<Search> searches = searchRepository.findAll();
        Assertions.assertNotEquals(searches.size(), 0);

        Search search = searches.get(0);
        Assertions.assertEquals(search.getKeyword(), "keyword4");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalSearch = this.searchRepository.findById(4L);

        if(optionalSearch.isPresent()) {
            var result = optionalSearch.get();
            Assertions.assertEquals(result.getKeyword(), "keyword4");

            var userId = 4L;
            result.setUserId(userId);
            Search merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getUserId(), 4L);
        } else {
            Assertions.assertNotNull(optionalSearch.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Search search = setUp(4L, "keyword4");
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



    private Search setUp(Long userId, String keyword) {
        Search search = new Search();
        search.setUserId(userId);
        search.setKeyword(keyword);
        return this.entityManager.persist(search);
    }
}
