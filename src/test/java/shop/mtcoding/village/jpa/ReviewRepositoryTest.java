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
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.review.ReviewRepository;
import shop.mtcoding.village.model.user.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init() {
        em.createNativeQuery("ALTER TABLE review_tb ALTER COLUMN ID RESTART WITH 4L").executeUpdate();
        User user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");
        setUp(user, 5, "내용4", "image4", 4);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Review> reviews = reviewRepository.findAll();
        Assertions.assertNotEquals(reviews.size(), 0);

        Review review = reviews.get(0);
        Assertions.assertEquals(review.getContent(), "내용4");
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalReview = this.reviewRepository.findById(4L);

        if(optionalReview.isPresent()) {
            var result = optionalReview.get();
            Assertions.assertEquals(result.getContent(), "내용4");

            var image = "image4";
            result.setImage(image);
            Review merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getImage(),"image4");
        } else {
            Assertions.assertNotNull(optionalReview.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        User user = new User();
        user = setUpByUser("love", "1234", "love@nate.com", "010-7474-1212", "USER", "profile");
        Review review =  setUp(user, 5, "내용5", "image5", 5);
        Optional<Review> findUser = this.reviewRepository.findById(review.getId());

        if(findUser.isPresent()) {
            var result = findUser.get();
            Assertions.assertEquals(result.getImage(), "image5");
            entityManager.remove(review);
            Optional<Review> deleteReview = this.reviewRepository.findById(review.getId());
            if (deleteReview.isPresent()) {
                Assertions.assertNull(deleteReview.get());
            }
        } else {
            Assertions.assertNotNull(findUser.get());
        }
    }

    private User setUpByUser(String name, String password, String email, String tel, String role, String profile) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setTel(tel);
        user.setRole(role);
        user.setProfile(profile);
        return this.entityManager.persist(user);
    }
    private Review setUp(User user, Integer starRating, String content, String image, Integer likeCount) {
        Review review = new Review();
        review.setUser(user);
        review.setStarRating(starRating);
        review.setContent(content);
        review.setImage(image);
        review.setLikeCount(likeCount);
        return this.entityManager.persist(review);
    }
}
