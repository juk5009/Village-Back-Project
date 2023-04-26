package shop.mtcoding.village.controller.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.service.SearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s/search")
public class SearchController {

    private final SearchService searchService;
    @GetMapping
    public ResponseEntity<List<SearchList>> searchPlacesByKeyword(@RequestParam String keyword) {
        List<SearchList> searchLists = searchService.searchPlacesByKeyword(keyword);
        return ResponseEntity.ok().body(searchLists);
    }

}
