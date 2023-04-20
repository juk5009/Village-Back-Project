package shop.mtcoding.village.dto.category.request;

import lombok.Getter;
import lombok.ToString;
import shop.mtcoding.village.model.category.Category;

@Getter
@ToString
public class CategorySaveDTO {

    private String categoryName;

    public Category toDEntity() {
        return new Category(categoryName, null);
    }
}
