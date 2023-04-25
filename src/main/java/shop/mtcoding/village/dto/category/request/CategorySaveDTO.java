package shop.mtcoding.village.dto.category.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.place.Place;

@Getter
@ToString
public class CategorySaveDTO {

    private CategoryDTO categoryName;

    @Setter
    @Getter
    @ToString
    public static class CategoryDTO extends Category {
        private String categoryName;
        private Place placeId;

        public Category toEntity(String name, Place id) {
            Category categoryName = new Category();
            categoryName.setPlace(id);
            categoryName.setCategoryName(name);
            return categoryName;
        }
    }
}
