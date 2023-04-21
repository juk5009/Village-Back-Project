package shop.mtcoding.village.dto.search;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.village.model.search.Search;
import shop.mtcoding.village.model.user.User;

public class SearchRequest {

    @Getter
    @Setter
    public static class SaveSearch {
        private String keyword;


        public Search toEntity() {


            return Search.builder()
                    .keyword(keyword)
                    .build();
        }

    }
}
