package shop.mtcoding.village.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.model.search.SearchRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public List<SearchList> searchPlacesByKeyword(String keyword) {

        List<SearchList> searchLists = searchRepository.searchPlacesByKeyword(keyword);
        return searchLists;
    }
}
