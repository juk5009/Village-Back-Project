package shop.mtcoding.village.controller.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;
import shop.mtcoding.village.dto.search.SearchRequest;
import shop.mtcoding.village.service.SearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;



    @GetMapping
    public ResponseEntity<List<SearchList>> searchPlacesByKeyword(@RequestParam String keyword) {
        List<SearchList> searchLists = searchService.검색(keyword);


            SearchRequest.SaveSearch saveSearch = new SearchRequest.SaveSearch();
            saveSearch.setKeyword(keyword);
            searchService.키워드저장(saveSearch);


        return ResponseEntity.ok().body(searchLists);
    }

    @GetMapping("/pricehigh")
    public ResponseEntity<List<SearchOrderby>> searchPlacesByPriceDescending() {
        List<SearchOrderby> SearchOrderbyDesc = searchService.높은가격순정렬();
        return ResponseEntity.ok(SearchOrderbyDesc);
    }

    @GetMapping("/pricelow")
    public ResponseEntity<List<SearchOrderby>> searchPlacesByPriceAscending() {
        List<SearchOrderby> SearchOrderbyDesc = searchService.낮은가격순정렬();
        return ResponseEntity.ok(SearchOrderbyDesc);
    }

}
