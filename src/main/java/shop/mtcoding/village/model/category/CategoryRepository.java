package shop.mtcoding.village.model.category;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
