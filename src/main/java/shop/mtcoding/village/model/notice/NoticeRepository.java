
package shop.mtcoding.village.model.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.village.model.search.Search;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
