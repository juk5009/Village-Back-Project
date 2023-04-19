package shop.mtcoding.village.dto.category.request;

import lombok.Getter;
import shop.mtcoding.village.model.category.Category;

@Getter
public class CategorySaveDTO {

    private String categoryName;

    public Category toEntity() {
        return new Category(categoryName, null);
    }
}
